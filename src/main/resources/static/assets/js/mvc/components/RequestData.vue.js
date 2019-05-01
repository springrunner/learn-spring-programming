export default {
    name: 'RequestData',
    template: `
        <div>
            <h2>Request Data</h2>
            <p>See the <code>showcase.mvc.data</code> package for the @Controller code</p>
            <ul>
                <text-link link="/data/param?foo=bar"></text-link>
                <text-link link="/data/group?param1=foo&param2=bar&param3=baz"></text-link>
                <text-link link="/data/path/foo"></text-link>
                <text-link link="/data/matrixvars;foo=bar/simple"></text-link>
                <text-link link="/data/matrixvars;foo=bar1/multiple;foo=bar2"></text-link>
                <text-link link="/data/header"></text-link>
                <text-link link="/data/cookie"></text-link>
                <text-link link="/data/body" method="post" content-type="text/plain" data="foo"></text-link>
                <text-link link="/data/entity" method="post" content-type="text/plain" data="foo"></text-link>
            </ul>
            <h2>Standard Resolvable Web Arguments</h2>
            <p>See the <code>showcase.mvc.data.standard</code> package for the @Controller code</p>
            <ul>
                <text-link link="/data/standard/request"></text-link>
                <text-link link="/data/standard/request/reader" method="post" content-type="text/plain" data="foo"></text-link>
                <text-link link="/data/standard/request/is" method="post" content-type="text/plain" data="foo"></text-link>
                <text-link link="/data/standard/response"></text-link>
                <text-link link="/data/standard/response/writer"></text-link>
                <text-link link="/data/standard/response/os"></text-link>
                <text-link link="/data/standard/session"></text-link>
            </ul>
            <h2>Custom Resolvable Web Arguments</h2>
            <p>See the <code>showcase.mvc.data.custom</code> package for the @Controller code</p>
            <ul>
                <text-link link="/data/custom"></text-link>
            </ul>
        </div>	    
    `,
    created() {
        this.$http.get('/data/init')
    }
}