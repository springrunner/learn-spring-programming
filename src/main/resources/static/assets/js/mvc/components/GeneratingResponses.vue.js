export default {
    name: 'GeneratingResponses',
    template: `
        <div>
            <h2>Generating Responses</h2>
            <p>See the <code>showcase.mvc.response</code> package for the @Controller code</p>
            <ul>
                <text-link link="/responses/annotation" text="@ResponseBody"></text-link>
                <text-link link="/responses/charset/accept" text="@ResponseBody (UTF-8 charset requested)"></text-link>
                <text-link link="/responses/charset/produce" text="@ResponseBody (UTF-8 charset produced)"></text-link>
                <text-link link="/responses/entity/status" text="ResponseEntity (custom status)"></text-link>
                <text-link link="/responses/entity/headers" text="ResponseEntity (custom headers)"></text-link>              
            </ul>
        </div>   
    `
}