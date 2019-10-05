export default {
    name: 'MessageConverter',
    template: `
        <div>
            <h2>Message Converter</h2>
            <p>See the <code>showcase.mvc.messageconverter</code> package for the @Controller code</p>
            <h6>StringHttpMessageConverter</h6>
            <ul>
                <text-link link="/messageconverter/string" method="post"  content-type="text/plain" data="foo" text="Read a String"></text-link>
                <text-link link="/messageconverter/string" text="Write a String"></text-link>              
            </ul>
            
            <h6>FormHttpMessageConverter</h6>
            <ul>
                <text-link link="/messageconverter/form" method="post" content-type="form" v-bind:data="{'foo':'bar','fruit':'apple'}" text="Read Form Data"></text-link>
                <text-link link="/messageconverter/form" text="Write Form Data"></text-link>
            </ul>
            
            <h6>Jaxb2RootElementHttpMessageConverter</h6>
            <ul>
                <text-link link="/messageconverter/xml" method="post" content-type="application/xml" data="<javaBean><foo>bar</foo><fruit>apple</fruit></javaBean>" text="Read XML"></text-link>
                <text-link link="/messageconverter/xml" accept="application/xml" text="Write XML via Accept=application/xml"></text-link>
                <text-link link="/messageconverter/xml.xml" text="Write XML via '.xml'"></text-link>
            </ul>
            
            <h6>MappingJackson2HttpMessageConverter</h6>
            <ul>
                <text-link link="/messageconverter/json" method="post" v-bind:data="{'foo':'bar','fruit':'apple'}" text="Read JSON"></text-link>
                <text-link link="/messageconverter/json" accept="application/json" text="Write JSON via Accept=application/json"></text-link>
                <text-link link="/messageconverter/json.json" text="Write JSON via '.json'"></text-link>
            </ul>
            
            <h6>AtomFeedHttpMessageConverter</h6>
            <ul>
                <text-link link="/messageconverter/atom" method="post" content-type="application/atom+xml" data='<?xml version="1.0" encoding="UTF-8"?><feed xmlns="http://www.w3.org/2005/Atom"><title>My Atom feed</title></feed>' text="Read Atom"></text-link>
                <text-link link="/messageconverter/atom" accept="application/atom+xml" text="Write Atom via Accept=application/atom+xml"></text-link>
            </ul>
            
            <h6>RssChannelHttpMessageConverter</h6>
            <ul>
                <text-link link="/messageconverter/rss" method="post" content-type="application/rss+xml" data='<?xml version="1.0" encoding="UTF-8"?><rss version="2.0"> <channel> <title>My RSS feed</title> <link>http://localhost:8080/messageconverter/rss</link> <description>Description</description> </channel> </rss>' text="Read Rss"></text-link>
                <text-link link="/messageconverter/rss" accept="application/rss+xml" text="Write Rss via Accept=application/rss+xml"></text-link>
            </ul>
        </div>   
    `
}