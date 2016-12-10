// Compiled by ClojureScript 1.7.228 {}
goog.provide('chromaticgliss.views.posts');
goog.require('cljs.core');
goog.require('reagent.core');
goog.require('clojure.string');
goog.require('ajax.core');
goog.require('chromaticgliss.views.shared');
chromaticgliss.views.posts.post_state = reagent.core.atom.call(null,new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"posts","posts",760043164),cljs.core.PersistentVector.EMPTY], null));
chromaticgliss.views.posts.handle_error = (function chromaticgliss$views$posts$handle_error(_){
return console.log("Error on request");
});
chromaticgliss.views.posts.restore_post = (function chromaticgliss$views$posts$restore_post(post_cur){
return ajax.core.GET.call(null,[cljs.core.str("/api/posts/id/"),cljs.core.str(cljs.core.deref.call(null,post_cur).call(null,new cljs.core.Keyword(null,"id","id",-1388402092)))].join(''),new cljs.core.PersistentArrayMap(null, 6, [new cljs.core.Keyword(null,"format","format",-1306924766),new cljs.core.Keyword(null,"json","json",1279968570),new cljs.core.Keyword(null,"response-format","response-format",1664465322),new cljs.core.Keyword(null,"json","json",1279968570),new cljs.core.Keyword(null,"keywords?","keywords?",764949733),true,new cljs.core.Keyword(null,"params","params",710516235),cljs.core.deref.call(null,post_cur),new cljs.core.Keyword(null,"handler","handler",-195596612),(function (){
return cljs.core.swap_BANG_.call(null,post_cur,cljs.core.assoc,new cljs.core.Keyword(null,"editing?","editing?",1646440800),false);
}),new cljs.core.Keyword(null,"error-handler","error-handler",-484945776),chromaticgliss.views.posts.handle_error], null));
});
chromaticgliss.views.posts.save_post = (function chromaticgliss$views$posts$save_post(post_cur){
return ajax.core.POST.call(null,[cljs.core.str("/api/posts/id/"),cljs.core.str(cljs.core.deref.call(null,post_cur).call(null,new cljs.core.Keyword(null,"id","id",-1388402092)))].join(''),new cljs.core.PersistentArrayMap(null, 6, [new cljs.core.Keyword(null,"format","format",-1306924766),new cljs.core.Keyword(null,"json","json",1279968570),new cljs.core.Keyword(null,"response-format","response-format",1664465322),new cljs.core.Keyword(null,"json","json",1279968570),new cljs.core.Keyword(null,"keywords?","keywords?",764949733),true,new cljs.core.Keyword(null,"params","params",710516235),cljs.core.select_keys.call(null,cljs.core.deref.call(null,post_cur),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"title","title",636505583),new cljs.core.Keyword(null,"slug","slug",2029314850),new cljs.core.Keyword(null,"body","body",-2049205669)], null)),new cljs.core.Keyword(null,"handler","handler",-195596612),(function (){
return cljs.core.swap_BANG_.call(null,post_cur,cljs.core.assoc,new cljs.core.Keyword(null,"editing?","editing?",1646440800),false);
}),new cljs.core.Keyword(null,"error-handler","error-handler",-484945776),(function (){
return chromaticgliss.views.posts.restore_post.call(null,post_cur);
})], null));
});
chromaticgliss.views.posts.confirm_delete = (function chromaticgliss$views$posts$confirm_delete(post_cur){
return null;
});
chromaticgliss.views.posts.post_editor = (function chromaticgliss$views$posts$post_editor(post_cur){
return new cljs.core.PersistentVector(null, 8, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"form","form",-1624062471),new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"id","id",-1388402092),[cljs.core.str("post-editor-"),cljs.core.str(cljs.core.deref.call(null,post_cur).call(null,new cljs.core.Keyword(null,"id","id",-1388402092)))].join(''),new cljs.core.Keyword(null,"class","class",-2030961996),"post-editor"], null),new cljs.core.PersistentVector(null, 5, 5, cljs.core.PersistentVector.EMPTY_NODE, [chromaticgliss.views.shared.input_field,post_cur,"text","Title: ",new cljs.core.Keyword(null,"title","title",636505583)], null),new cljs.core.PersistentVector(null, 5, 5, cljs.core.PersistentVector.EMPTY_NODE, [chromaticgliss.views.shared.input_field,post_cur,"text","Slug: ",new cljs.core.Keyword(null,"slug","slug",2029314850)], null),new cljs.core.PersistentVector(null, 4, 5, cljs.core.PersistentVector.EMPTY_NODE, [chromaticgliss.views.shared.markdown_editor,post_cur,"Body: ",new cljs.core.Keyword(null,"body","body",-2049205669)], null),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"input","input",556931961),new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"type","type",1174270348),"hidden",new cljs.core.Keyword(null,"value","value",305978217),cljs.core.deref.call(null,post_cur).call(null,new cljs.core.Keyword(null,"id","id",-1388402092))], null)], null),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"button","button",1456579943),new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"type","type",1174270348),"button",new cljs.core.Keyword(null,"on-click","on-click",1632826543),(function (){
return chromaticgliss.views.posts.save_post.call(null,post_cur);
})], null),"Save"], null),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"button","button",1456579943),new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"type","type",1174270348),"button",new cljs.core.Keyword(null,"on-click","on-click",1632826543),(function (){
return chromaticgliss.views.posts.restore_post.call(null,post_cur);
})], null),"Cancel"], null)], null);
});
chromaticgliss.views.posts.post_component = (function chromaticgliss$views$posts$post_component(post_cur){
return new cljs.core.PersistentVector(null, 8, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"div","div",1057191632),new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"id","id",-1388402092),[cljs.core.str("post-"),cljs.core.str(cljs.core.deref.call(null,post_cur).call(null,new cljs.core.Keyword(null,"id","id",-1388402092)))].join(''),new cljs.core.Keyword(null,"class","class",-2030961996),"post"], null),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"h1","h1",-1896887462),cljs.core.deref.call(null,post_cur).call(null,new cljs.core.Keyword(null,"title","title",636505583))], null),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"div","div",1057191632),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"class","class",-2030961996),"body"], null),cljs.core.deref.call(null,post_cur).call(null,new cljs.core.Keyword(null,"body","body",-2049205669))], null),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"div","div",1057191632),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"class","class",-2030961996),"author"], null),[cljs.core.str("author: "),cljs.core.str(cljs.core.deref.call(null,post_cur).call(null,new cljs.core.Keyword(null,"user_id","user_id",993497112)))].join('')], null),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"div","div",1057191632),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"class","class",-2030961996),"date"], null),cljs.core.deref.call(null,post_cur).call(null,new cljs.core.Keyword(null,"created_at","created_at",1484050750))], null),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"button","button",1456579943),new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"type","type",1174270348),"button",new cljs.core.Keyword(null,"on-click","on-click",1632826543),(function (){
if(cljs.core.contains_QMARK_.call(null,cljs.core.deref.call(null,chromaticgliss.views.posts.shared_state).call(null,new cljs.core.Keyword(null,"user","user",1532431356)),new cljs.core.Keyword(null,"session","session",1008279103))){
return cljs.core.swap_BANG_.call(null,post_cur,cljs.core.assoc,new cljs.core.Keyword(null,"editing?","editing?",1646440800),true);
} else {
return cljs.core.swap_BANG_.call(null,chromaticgliss.views.posts.shared_state,chromaticgliss.views.posts.show_login_QMARK_,true);
}
})], null),"Edit"], null),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"button","button",1456579943),new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"type","type",1174270348),"button",new cljs.core.Keyword(null,"on-click","on-click",1632826543),(function (){
return chromaticgliss.views.posts.confirm_delete.call(null,post_cur);
})], null),"Delete"], null)], null);
});
chromaticgliss.views.posts.post_lister = (function chromaticgliss$views$posts$post_lister(posts){
return new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"div","div",1057191632),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"id","id",-1388402092),"all_posts"], null),(function (){var iter__6914__auto__ = (function chromaticgliss$views$posts$post_lister_$_iter__12824(s__12825){
return (new cljs.core.LazySeq(null,(function (){
var s__12825__$1 = s__12825;
while(true){
var temp__4425__auto__ = cljs.core.seq.call(null,s__12825__$1);
if(temp__4425__auto__){
var s__12825__$2 = temp__4425__auto__;
if(cljs.core.chunked_seq_QMARK_.call(null,s__12825__$2)){
var c__6912__auto__ = cljs.core.chunk_first.call(null,s__12825__$2);
var size__6913__auto__ = cljs.core.count.call(null,c__6912__auto__);
var b__12827 = cljs.core.chunk_buffer.call(null,size__6913__auto__);
if((function (){var i__12826 = (0);
while(true){
if((i__12826 < size__6913__auto__)){
var vec__12830 = cljs.core._nth.call(null,c__6912__auto__,i__12826);
var i = cljs.core.nth.call(null,vec__12830,(0),null);
var post = cljs.core.nth.call(null,vec__12830,(1),null);
cljs.core.chunk_append.call(null,b__12827,(cljs.core.truth_(post.call(null,new cljs.core.Keyword(null,"editing?","editing?",1646440800)))?cljs.core.with_meta(new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [chromaticgliss.views.posts.post_editor,reagent.core.cursor.call(null,posts,new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [i], null))], null),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"key","key",-1516042587),[cljs.core.str(post.call(null,new cljs.core.Keyword(null,"id","id",-1388402092))),cljs.core.str("-ed")].join('')], null)):cljs.core.with_meta(new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [chromaticgliss.views.posts.post_component,reagent.core.cursor.call(null,posts,new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [i], null))], null),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"key","key",-1516042587),post.call(null,new cljs.core.Keyword(null,"id","id",-1388402092))], null))));

var G__12832 = (i__12826 + (1));
i__12826 = G__12832;
continue;
} else {
return true;
}
break;
}
})()){
return cljs.core.chunk_cons.call(null,cljs.core.chunk.call(null,b__12827),chromaticgliss$views$posts$post_lister_$_iter__12824.call(null,cljs.core.chunk_rest.call(null,s__12825__$2)));
} else {
return cljs.core.chunk_cons.call(null,cljs.core.chunk.call(null,b__12827),null);
}
} else {
var vec__12831 = cljs.core.first.call(null,s__12825__$2);
var i = cljs.core.nth.call(null,vec__12831,(0),null);
var post = cljs.core.nth.call(null,vec__12831,(1),null);
return cljs.core.cons.call(null,(cljs.core.truth_(post.call(null,new cljs.core.Keyword(null,"editing?","editing?",1646440800)))?cljs.core.with_meta(new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [chromaticgliss.views.posts.post_editor,reagent.core.cursor.call(null,posts,new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [i], null))], null),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"key","key",-1516042587),[cljs.core.str(post.call(null,new cljs.core.Keyword(null,"id","id",-1388402092))),cljs.core.str("-ed")].join('')], null)):cljs.core.with_meta(new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [chromaticgliss.views.posts.post_component,reagent.core.cursor.call(null,posts,new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [i], null))], null),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"key","key",-1516042587),post.call(null,new cljs.core.Keyword(null,"id","id",-1388402092))], null))),chromaticgliss$views$posts$post_lister_$_iter__12824.call(null,cljs.core.rest.call(null,s__12825__$2)));
}
} else {
return null;
}
break;
}
}),null,null));
});
return iter__6914__auto__.call(null,cljs.core.map.call(null,cljs.core.vector,cljs.core.iterate.call(null,cljs.core.inc,(0)),cljs.core.deref.call(null,posts)));
})()], null);
});
chromaticgliss.views.posts.render_posts = (function chromaticgliss$views$posts$render_posts(){
ajax.core.GET.call(null,"/api/posts",new cljs.core.PersistentArrayMap(null, 4, [new cljs.core.Keyword(null,"format","format",-1306924766),new cljs.core.Keyword(null,"json","json",1279968570),new cljs.core.Keyword(null,"response-format","response-format",1664465322),new cljs.core.Keyword(null,"json","json",1279968570),new cljs.core.Keyword(null,"keywords?","keywords?",764949733),true,new cljs.core.Keyword(null,"handler","handler",-195596612),(function (p1__12833_SHARP_){
return cljs.core.swap_BANG_.call(null,chromaticgliss.views.posts.post_state,cljs.core.assoc,new cljs.core.Keyword(null,"posts","posts",760043164),p1__12833_SHARP_.call(null,new cljs.core.Keyword(null,"results","results",-1134170113)));
})], null));

return reagent.core.render.call(null,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [chromaticgliss.views.posts.post_lister,reagent.core.cursor.call(null,chromaticgliss.views.posts.post_state,new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"posts","posts",760043164)], null))], null),document.getElementById("app"));
});
goog.exportSymbol('chromaticgliss.views.posts.render_posts', chromaticgliss.views.posts.render_posts);
