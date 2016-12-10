// Compiled by ClojureScript 1.7.228 {}
goog.provide('garden.core');
goog.require('cljs.core');
goog.require('garden.compiler');
/**
 * Convert a variable number of Clojure data structure to a string of
 *   CSS. The first argument may be a list of flags for the compiler.
 */
garden.core.css = (function garden$core$css(var_args){
var args__7207__auto__ = [];
var len__7200__auto___14107 = arguments.length;
var i__7201__auto___14108 = (0);
while(true){
if((i__7201__auto___14108 < len__7200__auto___14107)){
args__7207__auto__.push((arguments[i__7201__auto___14108]));

var G__14109 = (i__7201__auto___14108 + (1));
i__7201__auto___14108 = G__14109;
continue;
} else {
}
break;
}

var argseq__7208__auto__ = ((((0) < args__7207__auto__.length))?(new cljs.core.IndexedSeq(args__7207__auto__.slice((0)),(0))):null);
return garden.core.css.cljs$core$IFn$_invoke$arity$variadic(argseq__7208__auto__);
});

garden.core.css.cljs$core$IFn$_invoke$arity$variadic = (function (rules){
return cljs.core.apply.call(null,garden.compiler.compile_css,rules);
});

garden.core.css.cljs$lang$maxFixedArity = (0);

garden.core.css.cljs$lang$applyTo = (function (seq14106){
return garden.core.css.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq14106));
});
/**
 * Convert a variable number of maps into a string of CSS for use with
 *   the HTML `style` attribute.
 */
garden.core.style = (function garden$core$style(var_args){
var args__7207__auto__ = [];
var len__7200__auto___14111 = arguments.length;
var i__7201__auto___14112 = (0);
while(true){
if((i__7201__auto___14112 < len__7200__auto___14111)){
args__7207__auto__.push((arguments[i__7201__auto___14112]));

var G__14113 = (i__7201__auto___14112 + (1));
i__7201__auto___14112 = G__14113;
continue;
} else {
}
break;
}

var argseq__7208__auto__ = ((((0) < args__7207__auto__.length))?(new cljs.core.IndexedSeq(args__7207__auto__.slice((0)),(0))):null);
return garden.core.style.cljs$core$IFn$_invoke$arity$variadic(argseq__7208__auto__);
});

garden.core.style.cljs$core$IFn$_invoke$arity$variadic = (function (maps){
return garden.compiler.compile_style.call(null,maps);
});

garden.core.style.cljs$lang$maxFixedArity = (0);

garden.core.style.cljs$lang$applyTo = (function (seq14110){
return garden.core.style.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq14110));
});
