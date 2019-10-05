export default {
    name: 'ExceptionHandling',
    template: `
        <div>
            <h2>Exception Handling</h2>
            <p>See the <code>showcase.mvc.exception</code> package for the @Controller code</p>
            <ul>
                <text-link link="/exception" text="@ExceptionHandler in Controller"></text-link>
                <text-link link="/global-exception" text="Global @ExceptionHandler"></text-link>              
            </ul>
        </div>   
    `
}