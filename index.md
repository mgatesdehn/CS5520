---
layout: default
title: CS5520
---
# {{ page.title }}
	changed
<ul class="posts">

	  {% for post in site.posts %}
	    <li><a href="{{ post.url }}" title="{{ post.title }}">{{ post.title }}</a></li>
	  {% endfor %}
	</ul>