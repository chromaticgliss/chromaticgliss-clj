// Compiled by ClojureScript 1.7.228 {}
goog.provide('reagent.dom');
goog.require('cljs.core');
goog.require('reagent.impl.util');
goog.require('reagent.impl.template');
goog.require('reagent.debug');
goog.require('reagent.interop');
if(typeof reagent.dom.dom !== 'undefined'){
} else {
reagent.dom.dom = (function (){var or__6142__auto__ = (function (){var and__6130__auto__ = typeof ReactDOM !== 'undefined';
if(and__6130__auto__){
return ReactDOM;
} else {
return and__6130__auto__;
}
})();
if(cljs.core.truth_(or__6142__auto__)){
return or__6142__auto__;
} else {
var and__6130__auto__ = typeof require !== 'undefined';
if(and__6130__auto__){
return require("react-dom");
} else {
return and__6130__auto__;
}
}
})();
}
if(cljs.core.truth_(reagent.dom.dom)){
} else {
throw (new Error([cljs.core.str("Assert failed: "),cljs.core.str("Could not find ReactDOM"),cljs.core.str("\n"),cljs.core.str(cljs.core.pr_str.call(null,new cljs.core.Symbol(null,"dom","dom",403993605,null)))].join('')));
}
if(typeof reagent.dom.roots !== 'undefined'){
} else {
reagent.dom.roots = cljs.core.atom.call(null,cljs.core.PersistentArrayMap.EMPTY);
}
reagent.dom.unmount_comp = (function reagent$dom$unmount_comp(container){
cljs.core.swap_BANG_.call(null,reagent.dom.roots,cljs.core.dissoc,container);

return (reagent.dom.dom["unmountComponentAtNode"])(container);
});
reagent.dom.render_comp = (function reagent$dom$render_comp(comp,container,callback){
var _STAR_always_update_STAR_12433 = reagent.impl.util._STAR_always_update_STAR_;
reagent.impl.util._STAR_always_update_STAR_ = true;

try{return (reagent.dom.dom["render"])(comp.call(null),container,((function (_STAR_always_update_STAR_12433){
return (function (){
var _STAR_always_update_STAR_12434 = reagent.impl.util._STAR_always_update_STAR_;
reagent.impl.util._STAR_always_update_STAR_ = false;

try{cljs.core.swap_BANG_.call(null,reagent.dom.roots,cljs.core.assoc,container,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [comp,container], null));

if(cljs.core.some_QMARK_.call(null,callback)){
return callback.call(null);
} else {
return null;
}
}finally {reagent.impl.util._STAR_always_update_STAR_ = _STAR_always_update_STAR_12434;
}});})(_STAR_always_update_STAR_12433))
);
}finally {reagent.impl.util._STAR_always_update_STAR_ = _STAR_always_update_STAR_12433;
}});
reagent.dom.re_render_component = (function reagent$dom$re_render_component(comp,container){
return reagent.dom.render_comp.call(null,comp,container,null);
});
/**
 * Render a Reagent component into the DOM. The first argument may be
 *   either a vector (using Reagent's Hiccup syntax), or a React element. The second argument should be a DOM node.
 * 
 *   Optionally takes a callback that is called when the component is in place.
 * 
 *   Returns the mounted component instance.
 */
reagent.dom.render = (function reagent$dom$render(var_args){
var args12435 = [];
var len__7200__auto___12438 = arguments.length;
var i__7201__auto___12439 = (0);
while(true){
if((i__7201__auto___12439 < len__7200__auto___12438)){
args12435.push((arguments[i__7201__auto___12439]));

var G__12440 = (i__7201__auto___12439 + (1));
i__7201__auto___12439 = G__12440;
continue;
} else {
}
break;
}

var G__12437 = args12435.length;
switch (G__12437) {
case 2:
return reagent.dom.render.cljs$core$IFn$_invoke$arity$2((arguments[(0)]),(arguments[(1)]));

break;
case 3:
return reagent.dom.render.cljs$core$IFn$_invoke$arity$3((arguments[(0)]),(arguments[(1)]),(arguments[(2)]));

break;
default:
throw (new Error([cljs.core.str("Invalid arity: "),cljs.core.str(args12435.length)].join('')));

}
});

reagent.dom.render.cljs$core$IFn$_invoke$arity$2 = (function (comp,container){
return reagent.dom.render.call(null,comp,container,null);
});

reagent.dom.render.cljs$core$IFn$_invoke$arity$3 = (function (comp,container,callback){
var f = (function (){
return reagent.impl.template.as_element.call(null,((cljs.core.fn_QMARK_.call(null,comp))?comp.call(null):comp));
});
return reagent.dom.render_comp.call(null,f,container,callback);
});

reagent.dom.render.cljs$lang$maxFixedArity = 3;
reagent.dom.unmount_component_at_node = (function reagent$dom$unmount_component_at_node(container){
return reagent.dom.unmount_comp.call(null,container);
});
/**
 * Returns the root DOM node of a mounted component.
 */
reagent.dom.dom_node = (function reagent$dom$dom_node(this$){
return (reagent.dom.dom["findDOMNode"])(this$);
});
reagent.impl.template.find_dom_node = reagent.dom.dom_node;
/**
 * Force re-rendering of all mounted Reagent components. This is
 *   probably only useful in a development environment, when you want to
 *   update components in response to some dynamic changes to code.
 * 
 *   Note that force-update-all may not update root components. This
 *   happens if a component 'foo' is mounted with `(render [foo])` (since
 *   functions are passed by value, and not by reference, in
 *   ClojureScript). To get around this you'll have to introduce a layer
 *   of indirection, for example by using `(render [#'foo])` instead.
 */
reagent.dom.force_update_all = (function reagent$dom$force_update_all(){
var seq__12446_12450 = cljs.core.seq.call(null,cljs.core.vals.call(null,cljs.core.deref.call(null,reagent.dom.roots)));
var chunk__12447_12451 = null;
var count__12448_12452 = (0);
var i__12449_12453 = (0);
while(true){
if((i__12449_12453 < count__12448_12452)){
var v_12454 = cljs.core._nth.call(null,chunk__12447_12451,i__12449_12453);
cljs.core.apply.call(null,reagent.dom.re_render_component,v_12454);

var G__12455 = seq__12446_12450;
var G__12456 = chunk__12447_12451;
var G__12457 = count__12448_12452;
var G__12458 = (i__12449_12453 + (1));
seq__12446_12450 = G__12455;
chunk__12447_12451 = G__12456;
count__12448_12452 = G__12457;
i__12449_12453 = G__12458;
continue;
} else {
var temp__4425__auto___12459 = cljs.core.seq.call(null,seq__12446_12450);
if(temp__4425__auto___12459){
var seq__12446_12460__$1 = temp__4425__auto___12459;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__12446_12460__$1)){
var c__6945__auto___12461 = cljs.core.chunk_first.call(null,seq__12446_12460__$1);
var G__12462 = cljs.core.chunk_rest.call(null,seq__12446_12460__$1);
var G__12463 = c__6945__auto___12461;
var G__12464 = cljs.core.count.call(null,c__6945__auto___12461);
var G__12465 = (0);
seq__12446_12450 = G__12462;
chunk__12447_12451 = G__12463;
count__12448_12452 = G__12464;
i__12449_12453 = G__12465;
continue;
} else {
var v_12466 = cljs.core.first.call(null,seq__12446_12460__$1);
cljs.core.apply.call(null,reagent.dom.re_render_component,v_12466);

var G__12467 = cljs.core.next.call(null,seq__12446_12460__$1);
var G__12468 = null;
var G__12469 = (0);
var G__12470 = (0);
seq__12446_12450 = G__12467;
chunk__12447_12451 = G__12468;
count__12448_12452 = G__12469;
i__12449_12453 = G__12470;
continue;
}
} else {
}
}
break;
}

return "Updated";
});
