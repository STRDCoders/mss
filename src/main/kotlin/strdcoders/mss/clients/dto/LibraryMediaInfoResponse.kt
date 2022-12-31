package strdcoders.mss.clients.dto

data class MediaInfo(
    val added_at: String,
    val audio_channels: String,
    val audio_codec: String,
    val bitrate: String,
    val container: String,
    val file_size: Long,
    val grandparent_rating_key: String,
    val last_played: Long,
    val media_index: String,
    val media_type: String,
    val parent_media_index: String,
    val parent_rating_key: String,
    val play_count: Int,
    val rating_key: String,
    val section_id: Int,
    val section_type: String,
    val sort_title: String,
    val thumb: String,
    val title: String,
    val video_codec: String,
    val video_framerate: String,
    val video_resolution: String,
    val year: String,
)

data class LibraryMediaInfo(
    val draw: Int,
    val recordsTotal: Int,
    val recordsFiltered: Int,
    val filtered_file_size: Long,
    val total_file_size: Long,
    val data: List<MediaInfo>,
)
