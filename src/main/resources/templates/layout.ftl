<#ftl output_format="HTML" auto_esc=true>

<#macro page
    title="Helltar - homepage"
    description="Personal homepage featuring links, projects, and contacts."
    siteName="Helltar"
    siteUrl="https://helltar.com"
    canonicalUrl="/"
    ogType="website"
    imageUrl="/static/favicon/apple-touch-icon.png"
    twitterCard="summary_large_image"
    twitterSite="@Helltar"
    robots="index,follow"
>
<#assign resolvedCanonicalUrl = canonicalUrl?starts_with("http")?then(canonicalUrl, siteUrl + canonicalUrl)>
<#assign resolvedImageUrl = imageUrl?starts_with("http")?then(imageUrl, siteUrl + imageUrl)>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="author" content="Helltar">
    <meta name="description" content="${description}">
    <meta name="robots" content="${robots}">

    <title>${title}</title>
    <link rel="canonical" href="${resolvedCanonicalUrl}">

    <meta property="og:type" content="${ogType}">
    <meta property="og:site_name" content="${siteName}">
    <meta property="og:url" content="${resolvedCanonicalUrl}">
    <meta property="og:title" content="${title}">
    <meta property="og:description" content="${description}">
    <meta property="og:image" content="${resolvedImageUrl}">

    <meta name="twitter:card" content="${twitterCard}">
    <meta name="twitter:site" content="${twitterSite}">
    <meta name="twitter:title" content="${title}">
    <meta name="twitter:description" content="${description}">
    <meta name="twitter:image" content="${resolvedImageUrl}">

    <link rel="icon" type="image/x-icon" href="/static/favicon/favicon.ico">
    <link rel="apple-touch-icon" href="/static/favicon/apple-touch-icon.png">
    <link rel="stylesheet" media="screen" href="/static/css/style.css">
</head>
<body>
<main class="page-content">
    <#nested>
</main>
</body>
</html>
</#macro>
