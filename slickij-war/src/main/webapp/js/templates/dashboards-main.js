(function(){var a=Handlebars.template,b=Handlebars.templates=Handlebars.templates||{};b["dashboards-main.html"]=a(function(a,b,c,d,e){c=c||a.helpers;var f="",g,h=this,i="function",j=c.helperMissing,k=void 0,l=this.escapeExpression;return f+='<h1 class="center">',g=b.data,g=g===null||g===undefined||g===!1?g:g.project,g=g===null||g===undefined||g===!1?g:g.name,typeof g===i?g=g.call(b,{hash:{}}):g===k&&(g=j.call(b,"data.project.name",{hash:{}})),f+=l(g)+' Dashboard</h1>\n<div id="dashboardlets-container">\n</div>\n<div class="clear"></div>\n',f})})()