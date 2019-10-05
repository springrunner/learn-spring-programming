export default {
    name: 'GeneratingResponses',
    template: `
        <div>
            <h2>Generating Responses</h2>
            <p>See the <code>showcase.mvc.response</code> package for the @Controller code</p>
            <ul>
                <text-link link="/response/annotation" text="@ResponseBody"></text-link>
                <text-link link="/response/charset/accept" text="@ResponseBody (UTF-8 charset requested)"></text-link>
                <text-link link="/response/charset/produce" text="@ResponseBody (UTF-8 charset produced)"></text-link>
                <text-link link="/response/entity/status" text="ResponseEntity (custom status)"></text-link>
                <text-link link="/response/entity/headers" text="ResponseEntity (custom headers)"></text-link>              
            </ul>
        </div>   
    `
}