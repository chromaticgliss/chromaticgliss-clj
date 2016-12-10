// Compiled by ClojureScript 1.7.228 {}
goog.provide('chromaticgliss.views.forms');
goog.require('cljs.core');
goog.require('reagent.core');
goog.require('ajax.core');
chromaticgliss.views.forms.input_field = (function chromaticgliss$views$forms$input_field(var_args){
var args12494 = [];
var len__7200__auto___12497 = arguments.length;
var i__7201__auto___12498 = (0);
while(true){
if((i__7201__auto___12498 < len__7200__auto___12497)){
args12494.push((arguments[i__7201__auto___12498]));

var G__12499 = (i__7201__auto___12498 + (1));
i__7201__auto___12498 = G__12499;
continue;
} else {
}
break;
}

var G__12496 = args12494.length;
switch (G__12496) {
case 4:
return chromaticgliss.views.forms.input_field.cljs$core$IFn$_invoke$arity$4((arguments[(0)]),(arguments[(1)]),(arguments[(2)]),(arguments[(3)]));

break;
case 5:
return chromaticgliss.views.forms.input_field.cljs$core$IFn$_invoke$arity$5((arguments[(0)]),(arguments[(1)]),(arguments[(2)]),(arguments[(3)]),(arguments[(4)]));

break;
default:
throw (new Error([cljs.core.str("Invalid arity: "),cljs.core.str(args12494.length)].join('')));

}
});

chromaticgliss.views.forms.input_field.cljs$core$IFn$_invoke$arity$4 = (function (cur,type,label,field){
return chromaticgliss.views.forms.input_field.call(null,cur,type,label,field,(function (p1__12493_SHARP_){
return cljs.core.swap_BANG_.call(null,cur,cljs.core.assoc,field,p1__12493_SHARP_.target.value);
}));
});

chromaticgliss.views.forms.input_field.cljs$core$IFn$_invoke$arity$5 = (function (cur,type,label,field,fn){
return new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"div","div",1057191632),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"label","label",1718410804),label], null),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"input","input",556931961),new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Keyword(null,"type","type",1174270348),type,new cljs.core.Keyword(null,"value","value",305978217),cljs.core.deref.call(null,cur).call(null,field),new cljs.core.Keyword(null,"on-change","on-change",-732046149),fn], null)], null)], null);
});

chromaticgliss.views.forms.input_field.cljs$lang$maxFixedArity = 5;
