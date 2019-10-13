export default {
    name: 'Validation',
    template: `
        <div>
            <h2>Validation</h2>
            <p>See the <code>showcase.mvc.validation</code> package for the @Controller code</p>
            <ul>
                <text-link link="/validate?number=3&date=2029-07-04" text="Validate, no errors"></text-link>
                <text-link link="/validate?number=3&date=2010-07-01" text="Validate, errors"></text-link>
            </ul>
        </div>	    
    `
}