---
layout: default
title: CS5520
---
<ul class="posts">
changed nothing
	  {% for post in site.posts %}
	    <li><a href="/CS5520{{ post.url }}" title="{{ post.title }}">{{ post.title }}</a></li>
	  {% endfor %}
	</ul>
