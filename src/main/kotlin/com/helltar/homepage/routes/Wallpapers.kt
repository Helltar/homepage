package com.helltar.homepage.routes

import com.helltar.homepage.Config.WALLPAPERS_DIR
import io.ktor.http.*
import io.ktor.server.freemarker.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

fun Route.wallpapers() {

    get("/wallpapers") {
        val dir = File(WALLPAPERS_DIR)
        val imageExtensions = listOf("jpg", "png")

        val links =
            dir.listFiles()
                ?.filter { it.isFile && it.extension.lowercase() in imageExtensions }
                ?.sortedByDescending { it.lastModified() }
                ?.map { "/wallpapers/${it.name}" } ?: emptyList()

        call.respond(FreeMarkerContent("wallpapers.ftl", mapOf("links" to links)))
    }

    get("/wallpapers/thumb/{filename}") {
        val filename = call.parameters["filename"]

        if (filename == null) {
            call.respond(HttpStatusCode.BadRequest)
            return@get
        }

        val originalFile = File(WALLPAPERS_DIR, filename)

        if (!originalFile.exists()) {
            call.respond(HttpStatusCode.NotFound)
            return@get
        }

        val thumbsDir = File("$WALLPAPERS_DIR/thumbs").apply { mkdirs() }
        val thumbFile = File(thumbsDir, filename)

        if (!thumbFile.exists())
            generateThumbnail(originalFile, thumbFile)

        val fileToServe = if (thumbFile.exists()) thumbFile else originalFile

        call.respondFile(fileToServe)
    }
}

private fun generateThumbnail(originalFile: File, thumbFile: File, thumbHeight: Int = 128) {
    try {
        val originalImage = ImageIO.read(originalFile) ?: return
        val targetWidth = (originalImage.width.toDouble() / originalImage.height * thumbHeight).toInt()

        val thumbImage = BufferedImage(targetWidth, thumbHeight, BufferedImage.TYPE_INT_RGB)

        thumbImage.createGraphics().apply {
            drawImage(originalImage, 0, 0, targetWidth, thumbHeight, null)
            dispose()
        }

        val format = originalFile.extension.lowercase()
        ImageIO.write(thumbImage, format, thumbFile)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
