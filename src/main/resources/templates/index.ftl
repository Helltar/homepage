<!DOCTYPE html>

<#-- @ftlvariable name="projects" type="java.util.Set<com.helltar.plugins.ProjectData>" -->
<#-- @ftlvariable name="routePathStatic" type="java.lang.String" -->

<#assign githubBaseUrl="https://github.com/Helltar">
<#assign avatarUrl="https://avatars.githubusercontent.com/u/6628997?v=4">
<#assign youtubeUrl = "https://www.youtube.com/@Helltar">
<#assign googlePlayUrl = "https://play.google.com/store/apps/dev?id=6279216035258388805">

<#assign email = "iam@helltar.com">
<#assign signalUsername = "helltar.01">

<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="author" content="Helltar">

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

<div class="main">
    <span class="section-title">🔗</span>

    <a href="${githubBaseUrl}">github</a>
    <a href="${youtubeUrl}">youtube</a>
    <a href="${googlePlayUrl}">googleplay</a>

    <span class="section-title">🎯 projects</span>

    <#list projects as project>
        <a href="${githubBaseUrl}/${project.repo}">${project.name}</a>
        <span class="project-description">${project.about}</span>
        <span class="language-color" style="background-color: #A97BFF"></span> <span class="programming-language">${project.type}</span>

    </#list>
    <a href="${githubBaseUrl}?tab=repositories" class="programming-language">see all →</a>

    <span class="section-title">💬 contacts</span>

    signal: ${signalUsername}
    email: <a href="mailto:${email}">${email}</a>
</div>

</body>

<!-- sho tut? -->

</html>
