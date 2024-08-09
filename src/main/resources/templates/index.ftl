<!DOCTYPE html>

<#-- @ftlvariable name="projects" type="java.util.Set<com.helltar.plugins.ProjectData>" -->
<#-- @ftlvariable name="routePathStatic" type="java.lang.String" -->

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
    <img class="avatar" src="https://avatars.githubusercontent.com/u/6628997?v=4" alt="avatar" height="96" width="96">
    <div class="flag">🇺🇦</div>
</div>

<div class="main">
    <span class="title">🔗</span>

    <a href="https://github.com/helltar">github</a>
    <a href="https://www.youtube.com/@Helltar">youtube</a>
    <a href="https://play.google.com/store/apps/dev?id=6279216035258388805">googleplay</a>

    <span class="title">🎯 projects</span>

    <#list projects as project>
        <a href="https://github.com/Helltar/${project.repo}">${project.name}</a>
        <span class="project-about">${project.about}</span>
        <span class="language-color" style="background-color: #A97BFF"></span> <span class="programmingLanguage">${project.type}</span>

    </#list>
    <a href="https://github.com/Helltar?tab=repositories" class="programmingLanguage">see all →</a>

    <span class="title">💬 contacts</span>

    iam@helltar.com
    helltar.01 (signal)
</div>

</body>

<!-- sho tut? -->

</html>
