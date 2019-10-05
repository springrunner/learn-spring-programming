export default {
    name: 'Redirecting',
    template: `
        <div>
            <h2>Redirecting</h2>
            <p>See the <code>showcase.mvc.redirect</code> package for the @Controller code</p>
            <ul>
                <li><a v-on:click.stop.prevent="render" href="/redirect/uriTemplate" class="textLink">URI Template String</a></li>
                <li><a v-on:click.stop.prevent="render" href="/redirect/uriComponentsBuilder" class="textLink">UriComponentsBuilder</a></li>
            </ul>
            <div class="alert alert-secondary mt-2" role="alert">
                <strong>Note</strong> Clicking the links render the redirected view at the bottom.
            </div>
            <div class="embed-responsive embed-responsive-1by1" style="height: 200px;">
                <iframe v-if="screenVisibility" id="screen" src="/redirect/uriTemplate" class="embed-responsive-item" scrolling="no"></iframe>
            </div>
            <div class="mb-4"></div>
        </div>   
    `,
    methods: {
        render(event) {
            $('#screen').attr('src', $(event.target).attr('href'))
            this.screenVisibility = true
        }
    },
    data() {
        return {
            screenVisibility: false
        }
    }
}