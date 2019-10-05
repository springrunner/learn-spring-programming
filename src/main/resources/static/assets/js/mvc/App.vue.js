export default {
    name: 'App',
    template: `
        <div>
            <nav class="col-md-2 d-none d-md-block bg-light sidebar">
                <div class="sidebar-sticky">
                    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                        <span>showcase</span>
                        <a class="d-flex align-items-center text-muted" href="#">
                            <span data-feather="plus-circle"></span>
                        </a>
                    </h6>
                    <ul class="nav flex-column mb-2">
                        <li v-for="item in navItems" class="nav-item">
                            <router-link v-bind:to="item.link" class="nav-link">
                                <span v-bind:data-feather="item.feather"></span>{{ item.text }}
                            </router-link>
                        </li>
                    </ul>
                </div>
            </nav>
            <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
                <router-view/>
            </main>        
        </div>
    `,
    data() {
        return {
            navItems: [{
                link: '/simple',
                feather: 'file-text',
                text: 'Simple'
            },{
                link: '/request-mapping',
                feather: 'file-text',
                text: 'Request Mapping'
            },{
                link: '/request-data',
                feather: 'file-text',
                text: 'Request Data'
            },{
                link: '/forms',
                feather: 'file-text',
                text: 'Forms'
            },{
                link: '/file-upload',
                feather: 'file-text',
                text: 'File Upload'
            },{
                link: '/type-conversion',
                feather: 'file-text',
                text: 'Type Conversion'
            },{
                link: '/validation',
                feather: 'file-text',
                text: 'Validation'
            },{
                link: '/rendering-views',
                feather: 'file-text',
                text: 'Rendering Views'
            },{
                link: '/response-writing',
                feather: 'file-text',
                text: 'Response Writing'
            },{
                link: '/message-converter',
                feather: 'file-text',
                text: 'Message Converter'
            },{
                link: '/redirecting',
                feather: 'file-text',
                text: 'Redirecting'
            },{
                link: '/async-request',
                feather: 'file-text',
                text: 'Async Request'
            },{
                link: '/exception-handling',
                feather: 'file-text',
                text: 'Exception Handling'
            }]
        }
    },
    mounted() {
        feather.replace()
    }
}