import java.net.URLDecoder
import java.nio.charset.StandardCharsets

fun String.urlDecode(): String = URLDecoder.decode(this, StandardCharsets.UTF_8.toString())
