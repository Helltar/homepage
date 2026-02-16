<#ftl output_format="HTML" auto_esc=true>

<#-- @ftlvariable name="links" type="java.util.List<String>" -->

<#import "layout.ftl" as layout />
<@layout.page
    title="Helltar - Wallpapers"
    description="Wallpaper gallery."
    canonicalUrl="/wallpapers"
>

    <#if links?has_content>
        <div class="wallpaper-grid">
            <#list links as link>
                <#assign filename = link?keep_after_last('/')>
                <a href="${link}" title="${filename}">
                    <img class="wallpaper" src="/wallpapers/thumb/${filename}" alt="${filename}" loading="lazy" decoding="async">
                </a>
            </#list>
        </div>
    <#else>
        <p class="empty-state">No wallpapers available right now.</p>
    </#if>

    <div class="centered">
        <a href="/">back to home</a>
    </div>

</@layout.page>
