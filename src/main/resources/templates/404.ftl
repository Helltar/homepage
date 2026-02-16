<#ftl output_format="HTML" auto_esc=true>

<#import "layout.ftl" as layout />
<@layout.page
    title="404"
    description="Page not found."
    canonicalUrl="/404"
    robots="noindex,nofollow"
>

    <div class="centered">
        <img src="/static/images/404.gif" alt="404"/>
        <br>
        <a href="/">back to home</a>
    </div>

</@layout.page>
