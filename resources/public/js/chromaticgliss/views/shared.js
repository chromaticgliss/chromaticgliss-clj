// Compiled by ClojureScript 1.7.228 {}
goog.provide('chromaticgliss.views.shared');
goog.require('cljs.core');
goog.require('reagent.core');
goog.require('re_com.modal_panel');
goog.require('ajax.core');
chromaticgliss.views.shared.input_field = (function chromaticgliss$views$shared$input_field(var_args){
var args12795 = [];
var len__7200__auto___12798 = arguments.length;
var i__7201__auto___12799 = (0);
while(true){
if((i__7201__auto___12799 < len__7200__auto___12798)){
args12795.push((arguments[i__7201__auto___12799]));

var G__12800 = (i__7201__auto___12799 + (1));
i__7201__auto___12799 = G__12800;
continue;
} else {
}
break;
}

var G__12797 = args12795.length;
switch (G__12797) {
case 4:
return chromaticgliss.views.shared.input_field.cljs$core$IFn$_invoke$arity$4((arguments[(0)]),(arguments[(1)]),(arguments[(2)]),(arguments[(3)]));

break;
case 5:
return chromaticgliss.views.shared.input_field.cljs$core$IFn$_invoke$arity$5((arguments[(0)]),(arguments[(1)]),(arguments[(2)]),(arguments[(3)]),(arguments[(4)]));

break;
default:
throw (new Error([cljs.core.str("Invalid arity: "),cljs.core.str(args12795.length)].join('')));

}
});

chromaticgliss.views.shared.input_field.cljs$core$IFn$_invoke$arity$4 = (function (cur,type,label,field){
return chromaticgliss.views.shared.input_field.call(null,cur,type,label,field,(function (p1__12794_SHARP_){
return cljs.core.swap_BANG_.call(null,cur,cljs.core.assoc,field,p1__12794_SHARP_.target.value);
}));
});

chromaticgliss.views.shared.input_field.cljs$core$IFn$_invoke$arity$5 = (function (cur,type,label,field,fn){
return new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"div","div",1057191632),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"label","label",1718410804),label], null),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"input","input",556931961),new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Keyword(null,"type","type",1174270348),type,new cljs.core.Keyword(null,"value","value",305978217),cljs.core.deref.call(null,cur).call(null,field),new cljs.core.Keyword(null,"on-change","on-change",-732046149),fn], null)], null)], null);
});

chromaticgliss.views.shared.input_field.cljs$lang$maxFixedArity = 5;
chromaticgliss.views.shared.login_form = (function chromaticgliss$views$shared$login_form(){
var shared_state = reagent.core.atom.call(null,new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"user","user",1532431356),cljs.core.PersistentVector.EMPTY,new cljs.core.Keyword(null,"show-login?","show-login?",-445486656),false], null));
return new cljs.core.PersistentVector(null, 4, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"form","form",-1624062471),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"id","id",-1388402092),"login-form"], null),new cljs.core.PersistentVector(null, 5, 5, cljs.core.PersistentVector.EMPTY_NODE, [chromaticgliss.views.shared.input_field,reagent.core.cursor.call(null,shared_state,new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"user","user",1532431356)], null)),"text","Username: ",new cljs.core.Keyword(null,"username","username",1605666410)], null),new cljs.core.PersistentVector(null, 5, 5, cljs.core.PersistentVector.EMPTY_NODE, [chromaticgliss.views.shared.input_field,reagent.core.cursor.call(null,shared_state,new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"user","user",1532431356)], null)),"password","Password: ",new cljs.core.Keyword(null,"password","password",417022471)], null)], null);
});
/**
 * Modal for logging in to edit posts/pages/etc
 */
chromaticgliss.views.shared.login_modal = (function chromaticgliss$views$shared$login_modal(){
if(cljs.core.truth_(cljs.core.deref.call(null,chromaticgliss.views.shared.shared_state).call(null,new cljs.core.Keyword(null,"show-login?","show-login?",-445486656)))){
return new cljs.core.PersistentVector(null, 7, 5, cljs.core.PersistentVector.EMPTY_NODE, [chromaticgliss.views.shared.modal_panel,new cljs.core.Keyword(null,"backdrop-color","backdrop-color",1921200717),"grey",new cljs.core.Keyword(null,"backdrop-opacity","backdrop-opacity",1467395653),0.4,new cljs.core.Keyword(null,"child","child",623967545),new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [chromaticgliss.views.shared.login_form], null)], null);
} else {
return null;
}
});
chromaticgliss.views.shared.markdown_editor = (function chromaticgliss$views$shared$markdown_editor(var_args){
var args12803 = [];
var len__7200__auto___12806 = arguments.length;
var i__7201__auto___12807 = (0);
while(true){
if((i__7201__auto___12807 < len__7200__auto___12806)){
args12803.push((arguments[i__7201__auto___12807]));

var G__12808 = (i__7201__auto___12807 + (1));
i__7201__auto___12807 = G__12808;
continue;
} else {
}
break;
}

var G__12805 = args12803.length;
switch (G__12805) {
case 3:
return chromaticgliss.views.shared.markdown_editor.cljs$core$IFn$_invoke$arity$3((arguments[(0)]),(arguments[(1)]),(arguments[(2)]));

break;
case 4:
return chromaticgliss.views.shared.markdown_editor.cljs$core$IFn$_invoke$arity$4((arguments[(0)]),(arguments[(1)]),(arguments[(2)]),(arguments[(3)]));

break;
default:
throw (new Error([cljs.core.str("Invalid arity: "),cljs.core.str(args12803.length)].join('')));

}
});

chromaticgliss.views.shared.markdown_editor.cljs$core$IFn$_invoke$arity$3 = (function (cur,label,field){
return chromaticgliss.views.shared.markdown_editor.call(null,cur,label,field,(function (p1__12802_SHARP_){
return cljs.core.swap_BANG_.call(null,cur,cljs.core.assoc,field,p1__12802_SHARP_.target.value);
}));
});

chromaticgliss.views.shared.markdown_editor.cljs$core$IFn$_invoke$arity$4 = (function (cur,label,field,fn){
return new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"div","div",1057191632),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"label","label",1718410804),label], null),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"textarea","textarea",-650375824),new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"value","value",305978217),cljs.core.deref.call(null,cur).call(null,field),new cljs.core.Keyword(null,"on-change","on-change",-732046149),fn], null)], null)], null);
});

chromaticgliss.views.shared.markdown_editor.cljs$lang$maxFixedArity = 4;
