export default {
    name: 'ExceptionHandling',
    template: `
        <div>
            <h2>Exception Handling</h2>
            <p>See the <code>showcase.mvc.exception</code> package for the @Controller code</p>
            <ul>
                <text-link link="/exceptions/exception-handler" text="@ExceptionHandler in Controller"></text-link>
                <text-link link="/exceptions/global-exception-handler" text="@ExceptionHandler in ControllerAdvice"></text-link>
                <text-link link="/exceptions/response-status" text="@ResponseStatus in Exception"></text-link>
                <text-link link="/exceptions/mapping" text="Exception to View mapping"></text-link>
                <hr/>
                <text-link link="/exceptions/container-error-page/throw" text="Container Error Page"></text-link>              
            </ul>
        </div>
    `
}