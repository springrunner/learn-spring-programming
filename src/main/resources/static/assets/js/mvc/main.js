import App from './App.vue.js'
import Home from './components/Home.vue.js'
import MvcUtil from './mvc.util.js'

Vue.use(VueRouter)
Vue.config.productionTip = false
Vue.prototype.$http = axios

const router = new VueRouter({
    routes: [{
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
        text: {
            type: String,
            default: ''
        }
    },
    computed: {
        resolveId() {
            return this.id !== '' ? this.id : 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
                var r = Math.random()*16|0, v = c === 'x' ? r : (r&0x3|0x8);
                return v.toString(16);
            })
        },
        resolveText() {
            return this.text !== '' ? this.text : 'GET ' + this.link
        }
    },
    methods: {
        request(event) {
            const link = $(event.target)
            axios.get(event.srcElement.href).then(response => {
                MvcUtil.showSuccessResponse(response.data, link)
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