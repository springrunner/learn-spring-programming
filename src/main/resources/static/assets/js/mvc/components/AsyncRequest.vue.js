import MvcUtil from "../mvc.util.js";

export default {
    name: 'AsyncRequest',
    template: `
        <div>
            <h2>Async Request</h2>
            <p>See the <code>showcase.mvc.async</code> package for the @RequestMapping code</p>
            <div class="alert alert-secondary mt-2" role="alert">
                <strong>Note</strong> Links may take 2-3 seconds to complete.
            </div>
            
            <h6>DeferredResult</h6>
            <ul>                
                <text-link link="/async/deferred-result/response-body"></text-link>
                <text-link link="/async/deferred-result/model-and-view"></text-link>
                <text-link link="/async/deferred-result/exception"></text-link>
                <text-link link="/async/deferred-result/timeout-value"></text-link>
            </ul>
            
            <h6>Callable</h6>
            <ul>                
                <text-link link="/async/callable/response-body"></text-link>
                <text-link link="/async/callable/view"></text-link>
                <text-link link="/async/callable/exception"></text-link>
                <text-link link="/async/callable/exception?handled=false"></text-link>
                <text-link link="/async/callable/custom-timeout-handling"></text-link>
            </ul>
            
            <h6>HTTP Streaming</h6>
            <ul>
                <li><a v-on:click.stop.prevent="fetchTextStream" id="async-streaming-response-body-emitter" href="/async/streaming/response-body-emitter" class="textLink">GET /async/streaming/response-body-emitter</a></li>
                <li><a v-on:click.stop.prevent="fetchEvent" id="async-streaming-sse-emitter" href="/async/streaming/sse-emitter" class="textLink">GET /async/streaming/sse-emitter (Server-send Events)</a></li>
                <li><a v-on:click.stop.prevent="fetchTextStream" id="async-streaming-streaming-response-body" href="/async/streaming/streaming-response-body" class="textLink">GET /async/streaming/streaming-response-body (Raw Data)</a></li>                
            </ul>
            <div class="alert alert-warning" role="alert">
                Internet Explorer does not support Server-Sent Events                
            </div>
             
            <h6>Reactive Types</h6>
            <ul>
                <text-link link="/async/reactive/mono/response-body"></text-link>
                <text-link link="/async/reactive/mono/model-and-view"></text-link>
                <li><a v-on:click.stop.prevent="fetchTextStream" id="async-reactive-flux-response-body" href="/async/reactive/flux/response-body" class="textLink">GET /async/reactive/flux/response-body</a></li>
            </ul>
            
            <div class="mb-4"></div>
        </div>	    
    `,
    methods: {
        fetchTextStream(event) {
            const link = $(event.target)
            const url = event.srcElement.href

            try {
                const request = new XMLHttpRequest()
                let receivedIndex = 0

                request.onloadstart = () => {
                    MvcUtil.showSuccessResponse('Streaming startup.', link)
                }
                request.onprogress = () => {
                    if ((request.readyState === 3 || request.readyState === 2) && request.status === 200) {
                        const responseText = request.response.slice(receivedIndex);
                        if(responseText) {
                            MvcUtil.showSuccessResponse('Streaming response: ' + responseText, link)
                        }
                        receivedIndex = request.response.length
                    }
                }
                request.onload = () => {
                    if (request.status === 200) {
                        MvcUtil.showSuccessResponse('Streaming completed.', link)
                    }
                }
                request.onerror = error => {
                    MvcUtil.showErrorResponse(error, link)
                }
                request.ontimeout = () => {
                    MvcUtil.showErrorResponse('Streaming timeout!', link)
                }

                request.open("GET", url, true)
                request.setRequestHeader('Accept', 'application/octet-stream')
                request.send()
            } catch (error) {
                MvcUtil.showErrorResponse(error, link)
            }
        },
        fetchEvent(event) {
            const link = $(event.target)
            const url = event.srcElement.href

            const eventSource = new EventSource(url)
            eventSource.addEventListener('open', () => {
                MvcUtil.showSuccessResponse('Streaming startup.', link)
            })
            eventSource.addEventListener('counting', event => {
                MvcUtil.showSuccessResponse('Streaming response: ' + event.data, link)
            })
            eventSource.addEventListener('complete', event => {
                MvcUtil.showSuccessResponse('Streaming completed.', link)
                eventSource.close()
            })
            eventSource.addEventListener('error', error => {
                MvcUtil.showErrorResponse('Streaming error.', link)
                console.log(error)
            })
        }
    }
}