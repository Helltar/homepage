<#ftl output_format="HTML" auto_esc=true>

<#-- @ftlvariable name="projects" type="java.util.List<com.helltar.homepage.updaters.GitHubRepository>" -->

<#assign githubBaseUrl="https://github.com/Helltar">
<#assign youtubeUrl = "https://www.youtube.com/@Helltar">
<#assign googlePlayUrl = "https://play.google.com/store/apps/dev?id=6279216035258388805">
<#assign email = "iam@helltar.com">
<#assign signalLink = "https://signal.me/#eu/y5XuIZWoKC10lplufk6pe6uDF5Mej1t2H6-heyEmqJuoUc4uMI1OUJaKb5nS_drX">
<#assign avatarUrl="https://avatars.githubusercontent.com/u/6628997?v=4">

<#import "layout.ftl" as layout />
<@layout.page
    title="Helltar - Home"
    description="Helltar homepage with links, projects, and contacts."
    canonicalUrl="/"
>

<div class="avatar-container">
    <img class="avatar" src="${avatarUrl}" alt="avatar" height="96" width="96">
</div>

<div class="section-title">links</div>

<p><a href="${githubBaseUrl}">github</a></p>
<p><a href="${youtubeUrl}">youtube</a></p>
<p><a href="${googlePlayUrl}">googleplay</a></p>

<div class="section-title">projects</div>

<#if projects?has_content>
    <#list projects as project>
        <#if project??>
            <div class="project">
                <a href="${project.url!"#"}">${project.name!"untitled project"}</a>
                <span class="project-description">${project.description!"No description provided."}</span>
                <span class="language-indicator"></span>
                <span class="programming-language">${(project.primaryLanguage.name)!"Unknown"}</span>
            </div>
        </#if>
    </#list>
<#else>
    <p class="empty-state">Projects are temporarily unavailable.</p>
</#if>

<a class="see-all-link" href="${githubBaseUrl}?tab=repositories">see all →</a>

<div class="section-title">misc</div>

<p><a href="/wallpapers">wallpapers</a></p>

<div class="section-title">contacts</div>

<p><a href="${signalLink}">signal.me</a></p>
<p><a href="mailto:${email}">${email}</a></p>

<!-- sho tut? -->

</@layout.page>
