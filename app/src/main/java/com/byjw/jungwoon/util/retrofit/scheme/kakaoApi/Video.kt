package com.byjw.jungwoon.util.retrofit.scheme.kakaoApi

import com.byjw.jungwoon.util.retrofit.scheme.BaseContent

data class Video (
    val meta: VideoMeta,
    val documents: List<VideoDocument>
) : BaseContent

data class VideoMeta(
    val total_count: Int, // 검색어에 검색된 문서수
    val pageable_count: Int, // total_count 중에 노출가능 문서수
    val is_end: Boolean // 현재 페이지가 마지막 페이지인지 여부. 값이 false이면 page를 증가시켜 다음 페이지를 요청할 수 있음
) : BaseContent.Meta

data class VideoDocument(
    val title: String, // 동영상 제목
    val url: String, // 동영상 링크
    val datetime: String, // 동영상 등록일
    val play_time: Int, // 동영상 재생시간 (단위: 초)
    val thumbnail: String, // 동영상 썸네일 URL
    val author: String, // 동영상 업로더
    override var favorite: Boolean = false
) : BaseContent.Document