import App from './App.vue.js'
import Home from './components/Home.vue.js'
import Simple from './components/Simple.vue.js'
import RequestMapping from './components/RequestMapping.vue.js'
import RequestData from './components/RequestData.vue.js'
import Forms from './components/Forms.vue.js'
import FileUpload from './components/FileUpload.vue.js'
import TypeConversion from './components/TypeConversion.vue.js'
import MvcUtil from './mvc.util.js'

Vue.use(VueRouter)
Vue.config.productionTip = false
Vue.prototype.$http = axios

axios.defaults.timeout = 2000
axios.defaults.withCredentials = true

const router = new VueRouter({
    routes: [{
        path: '/simple',
        component: Simple
    },{
        path: '/request-mapping',
        component: RequestMapping
    },{
        path: '/request-data',
        component: RequestData
    },{
        path: '/forms',
        component: Forms
    },{
        path: '/file-upload',
        component: FileUpload
    },{
        path: '/type-conversion',
        component: TypeConversion
    },{
        path: '/',
        component: Home
    },{
        path: '*',
        redirect: '/'
    }]
})

Vue.component('text-link', {
    template: `<li><a v-on:click.stop.prevent="request" v-bind:id="resolveId" v-bind:href="link" class="textLink">{{ resolveText }}</a></li>`,
    props: {
        id: {
            type: String,
            default: ''
        },
        link: {
            type: String,
            required: true
        },
        method: {
            type: String,
            default: 'get'
        },
        accept: {
            type: String,
            default: ''
        },
        contentType: {
            type: String,
            default: ''
        },
        headers: {
            type: Object,
            default: {}
        },
        params: {
            type: Object,
            default: {}
        },
        data: {
            type: Object,
            default: {}
        },
        text: {
            type: String,
            default: ''
        }
    },
    computed: {
        resolveId() {
            return this.id !== '' ? this.id : 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
                let r = Math.random()*16|0, v = c === 'x' ? r : (r&0x3|0x8);
                return v.toString(16);
            })
        },
        resolveText() {
            return this.text !== '' ? this.text : this.method.toUpperCase() + ' ' + this.link
        }
    },
    methods: {
        request(event) {
            const link = $(event.target)
            const method = this.method
            const headers = this.headers
            const url = event.srcElement.href
            const params = this.params
            const data = this.data

            if (MvcUtil.isNotEmpty(this.accept)) {
                headers['Accept'] = this.accept
            }
            if (MvcUtil.isNotEmpty(this.contentType)) {
                headers['Content-Type'] = this.contentType
            }

            axios({method, url, headers, params, data}).then(response => {
                let responseText = response.request.responseText
                if (response.request.responseXML !== null) {
                    responseText = MvcUtil.xmlencode(response.request.responseXML)
                }
                MvcUtil.showSuccessResponse(responseText, link)
            }).catch(error => {
                MvcUtil.showErrorResponse(error, link)
            }).finally(() => {

            })
        }
    }
})

new Vue({
  render: h => h(App), router,
}).$mount(`#app`)