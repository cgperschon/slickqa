(function(){var a=Handlebars.template,b=Handlebars.templates=Handlebars.templates||{};b["not-found.html"]=a(function(a,b,c,d,e){function o(a,b){var c="",d;return c+="\n        <p>The url you tried to load was ",d=a.options,d=d===null||d===undefined||d===!1?d:d.url,typeof d===k?d=d.call(a,{hash:{}}):d===m&&(d=l.call(a,"options.url",{hash:{}})),c+=n(d)+".</p>\n    ",c}c=c||a.helpers;var f="",g,h,i,j=this,k="function",l=c.helperMissing,m=void 0,n=this.escapeExpression;f+='<img src="images/page-not-found.png" />\n<div class="box">\n\tUnfortunately the page you have come to is not real.\n    ',g=b.options,g=g===null||g===undefined||g===!1?g:g.url,h=c["if"],i=j.program(1,o,e),i.hash={},i.fn=i,i.inverse=j.noop,g=h.call(b,g,i);if(g||g===0)f+=g;return f+="\n</div>\n",f})})()