export default {
    name: 'TypeConversion',
    template: `
        <div>
            <h2>Type Conversion</h2>
            <p>See the <code>showcase.mvc.convert</code> package for the @Controller code</p>
            <ul>
                <text-link link="/convert/primitive?value=3" text="Primitive"></text-link>
                <text-link link="/convert/date/2010-07-04" text="@Formatted Date"></text-link>
                <text-link link="/convert/collection?values=1&values=2&values=3&values=4&values=5" text="Collection 1 (multi-value parameter)"></text-link>
                <text-link link="/convert/collection?values=1,2,3,4,5" text="Collection 2 (single comma-delimited parameter value)"></text-link>
                <text-link link="/convert/formattedCollection?values=2010-07-04,2011-07-04" text="@Formatted Date Collection"></text-link>
                <text-link link="/convert/value?value=123456789" text="Custom Value Object"></text-link>
                <text-link link="/convert/custom?value=123-45-6789" text="Custom Formatter"></text-link>
            </ul>
            <h2>JavaBean Property Binding</h2>
            <ul>
                <text-link link="/convert/bean?primitive=3" text="Primitive"></text-link>
                <text-link link="/convert/bean?date=2010-07-04" text="@Formatted Date"></text-link>
                <text-link link="/convert/bean?masked=(205) 333-3333" text="Custom Formatter"></text-link>
                <text-link link="/convert/bean?list[0]=1&list[1]=2&list[2]=3" text="List Elements"></text-link>
                <text-link link="/convert/bean?formattedList[0]=2010-07-04&formattedList[1]=2011-07-04" text="@Formatted Date List Elements"></text-link>
                <text-link link="/convert/bean?map[0]=apple&map[1]=pear" text="Map Elements"></text-link>
                <text-link link="/convert/bean?nested.foo=bar&nested.list[0].foo=baz&nested.map[key].list[0].foo=bip" text="Nested JavaBean"></text-link>
            </ul>
        </div>	    
    `
}