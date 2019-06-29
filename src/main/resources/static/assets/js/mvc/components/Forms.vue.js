import MvcUtil from '../mvc.util.js'

export default {
    name: 'Forms',
    template: `
        <div>
            <h2>Forms</h2>
            <p>See the <code>showcase.mvc.form</code> package for the @Controller code</p>
            <div class="embed-responsive embed-responsive-1by1" style="height: 600px;">
                <iframe src="/form" class="embed-responsive-item" scrolling="no"></iframe>
            </div>
            <h2>Ajax Forms</h2>
            <p>See the <code>showcase.mvc.form</code> package for the @RestController code</p>
            <form id="ajaxForm" class="mb-1">
                <div class="form-row align-items-center">
                    <div class="col-auto">
                        <button v-on:click="ajaxSubmit" type="button" class="btn btn-primary">Submit</button>
                    </div>
                    <div class="col-auto">
                        <label class="sr-only" for="usernameInput">Username</label>
                        <input v-model="form.username" type="email" class="form-control" placeholder="Email" name="username">
                    </div>
                    <div class="col-auto">
                        <label class="sr-only" for="passwordInput">Password</label>
                        <input v-model="form.password" type="password" class="form-control" placeholder="Password" name="password">
                    </div>
                    <div class="col-auto">
                        <div class="form-check">
                            <input v-model="form.rememberMe" class="form-check-input" type="checkbox" id="rememberMeCheck" name="rememberMe">
                            <label class="form-check-label" for="rememberMeCheck">Remember me</label>
                        </div>
                    </div>
                </div>
            </form>
            <div class="mb-4"></div>
        </div>	    
    `,
    data() {
        return {
            form: {
                username: '',
                password: '',
                rememberMe: false
            }
        }
    },
    methods: {
        ajaxSubmit() {
            const link = $('#ajaxForm')
            const method = 'post'
            const url = '/form'
            const headers = {}
            const params = {}
            const data = this.form

            axios({method, url, headers, params, data}).then(response => {
                MvcUtil.showSuccessResponse(response.request.responseText, link)
            }).catch(error => {
                MvcUtil.showErrorResponse(error.response.request.responseText, link)
            }).finally(() => {

            })
        }
    }
}