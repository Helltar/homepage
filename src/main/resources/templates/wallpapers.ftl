<#-- @ftlvariable name="links" type="java.util.List<String>" -->

<#import "layout.ftl" as layout />
<@layout.header>

    <#list links as link>
        <#assign filename = link?keep_after_last('/')>
        <a href="${link}"><img class="wallpaper" src="/wallpapers/thumb/${filename}" alt="${filename}"></a>
    </#list>

</@layout.header>
