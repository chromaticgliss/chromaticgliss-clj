// Compiled by ClojureScript 1.7.228 {}
goog.provide('chromaticgliss.views.login');
goog.require('cljs.core');
goog.require('reagent.core');
goog.require('ajax.core');
goog.require('chromaticgliss.views.shared');
chromaticgliss.views.login.login_state = reagent.core.atom.call(null,new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Keyword(null,"email","email",1415816706),null,new cljs.core.Keyword(null,"password","password",417022471),null,new cljs.core.Keyword(null,"token","token",-1211463215),null], null));
chromaticgliss.views.login.login_auth_interceptor = ajax.core.to_interceptor.call(null,new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"name","name",1843675177),"Authorization Interceptor",new cljs.core.Keyword(null,"request","request",1772954723),(function (p1__12812_SHARP_){
return cljs.core.assoc.call(null,p1__12812_SHARP_,new cljs.core.Keyword(null,"headers","headers",-835030129),new cljs.core.PersistentArrayMap(null, 1, ["Authorization",[cljs.core.str("Token "),cljs.core.str(cljs.core.deref.call(null,chromaticgliss.views.login.login_state).call(null,new cljs.core.Keyword(null,"token","token",-1211463215)))].join('')], null));
})], null));
chromaticgliss.views.login.login_session = (function chromaticgliss$views$login$login_session(){
return ajax.core.POST.call(null,"/api/sessions",new cljs.core.PersistentArrayMap(null, 5, [new cljs.core.Keyword(null,"format","format",-1306924766),new cljs.core.Keyword(null,"json","json",1279968570),new cljs.core.Keyword(null,"response-format","response-format",1664465322),new cljs.core.Keyword(null,"json","json",1279968570),new cljs.core.Keyword(null,"keywords?","keywords?",764949733),true,new cljs.core.Keyword(null,"params","params",710516235),cljs.core.dissoc.call(null,cljs.core.deref.call(null,chromaticgliss.views.login.login_state),new cljs.core.Keyword(null,"token","token",-1211463215)),new cljs.core.Keyword(null,"handler","handler",-195596612),(function (p1__12813_SHARP_){
return cljs.core.swap_BANG_.call(null,chromaticgliss.views.login.login_state,cljs.core.assoc,new cljs.core.Keyword(null,"token","token",-1211463215),p1__12813_SHARP_.call(null,new cljs.core.Keyword(null,"auth-token","auth-token",30990976)));
})], null));
});
chromaticgliss.views.login.login_form = (function chromaticgliss$views$login$login_form(){
return new cljs.core.PersistentVector(null, 5, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"form","form",-1624062471),new cljs.core.PersistentVector(null, 5, 5, cljs.core.PersistentVector.EMPTY_NODE, [chromaticgliss.views.shared.input_field,chromaticgliss.views.login.login_state,"text","Email: ",new cljs.core.Keyword(null,"email","email",1415816706)], null),new cljs.core.PersistentVector(null, 5, 5, cljs.core.PersistentVector.EMPTY_NODE, [chromaticgliss.views.shared.input_field,chromaticgliss.views.login.login_state,"password","Password: ",new cljs.core.Keyword(null,"password","password",417022471)], null),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"div","div",1057191632),new cljs.core.Keyword(null,"token","token",-1211463215).cljs$core$IFn$_invoke$arity$1(cljs.core.deref.call(null,chromaticgliss.views.login.login_state))], null),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"button","button",1456579943),new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"type","type",1174270348),"button",new cljs.core.Keyword(null,"on-click","on-click",1632826543),(function (){
return chromaticgliss.views.login.login_session.call(null);
})], null),"Save"], null)], null);
});
chromaticgliss.views.login.render_login = (function chromaticgliss$views$login$render_login(){
cljs.core.swap_BANG_.call(null,ajax.core.default_interceptors,cljs.core.partial.call(null,cljs.core.cons,chromaticgliss.views.login.login_auth_interceptor));

console.log(cljs.core.clj__GT_js.call(null,cljs.core.deref.call(null,chromaticgliss.views.login.login_state)));

return reagent.core.render.call(null,new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [chromaticgliss.views.login.login_form], null),document.getElementById("app"));
});
goog.exportSymbol('chromaticgliss.views.login.render_login', chromaticgliss.views.login.render_login);
