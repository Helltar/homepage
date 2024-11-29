<!DOCTYPE html>

<#-- @ftlvariable name="projects" type="java.util.Set<com.helltar.homepage.routes.models.ProjectData>" -->
<#-- @ftlvariable name="routePathStatic" type="java.lang.String" -->

<#assign githubBaseUrl="https://github.com/Helltar">
<#assign youtubeUrl = "https://www.youtube.com/@Helltar">
<#assign googlePlayUrl = "https://play.google.com/store/apps/dev?id=6279216035258388805">

<#assign email = "iam@helltar.com">
<#assign signalLink = "https://signal.me/#eu/y5XuIZWoKC10lplufk6pe6uDF5Mej1t2H6-heyEmqJuoUc4uMI1OUJaKb5nS_drX">

<#assign avatarUrl="https://avatars.githubusercontent.com/u/6628997?v=4">

<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="author" content="Helltar">
    <meta name="description" content="Personal homepage featuring links to my social media profiles, email contact, and a list of my GitHub projects. Stay connected and explore my work!">

    <title>Helltar - homepage</title>

    <link rel="icon" type="image/x-icon" href="${routePathStatic}/favicon/favicon.ico">
    <link rel="apple-touch-icon" href="${routePathStatic}/favicon/apple-touch-icon.png">
    <link rel="stylesheet" media="screen" href="${routePathStatic}/css/style.css">
</head>

<body>

<div class="avatar-container">
    <img class="avatar" src="${avatarUrl}" alt="avatar" height="96" width="96">
    <div class="flag">🇺🇦</div>
</div>

<div class="section-title">🔗</div>

<a href="${githubBaseUrl}">github</a><br>
<a href="${youtubeUrl}">youtube</a><br>
<a href="${googlePlayUrl}">googleplay</a><br>

<div class="section-title">🎯 projects</div>

<#list projects as project>
    <div class="project">
        <a href="${githubBaseUrl}/${project.repo}">${project.name}</a>
        <span class="project-description">${project.about}</span>
        <span class="kotlin-indicator"></span>
        <span class="programming-language">${project.type}</span>
    </div>
</#list>

<a href="${githubBaseUrl}?tab=repositories" style="font-size: medium">see all →</a>

<div class="section-title">💬 contacts</div>

<p><a href="${signalLink}">signal.me</a></p>
<p><a href="mailto:${email}">${email}</a></p>

</body>

<!-- sho tut? -->

</html>
