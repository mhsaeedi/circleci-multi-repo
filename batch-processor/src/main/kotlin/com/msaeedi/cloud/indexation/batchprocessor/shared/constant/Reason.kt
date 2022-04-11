package com.msaeedi.cloud.indexation.batchprocessor.shared.constant

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 23.09.21, Thu
 *
 **/
enum class Reason(verdict: Verdict, description: String) {
	MALFORMED_LINE(Verdict.FALSE, "Source data line was corrupted"),
	MALFORMED_WEEKLY_DAILY(Verdict.FALSE, "Source data has invalid weeklyDaily"),
	MALFORMED_ITEM_COUNT(Verdict.FALSE, "Source data has invalid itemCount"),
	MALFORMED_SHOP_STORE_COUNT(Verdict.FALSE, "Source data has invalid shopStoreCount"),
	MALFORMED_TAG_TYPE(Verdict.FALSE, "Source data has invalid tagtype"),
	MALFORMED_TAG_SET(Verdict.FALSE, "Source data has invalid tagSet"),
	NULL_DEEPEST_TAG(Verdict.FALSE, "Deepest tag is null"),
	REASON_INVALID_TAG_SET(Verdict.FALSE, "found invalid tag set"),
	REASON_REDIRECT_TAG(Verdict.FALSE, "TagSet contains redirect tag"),
	CONTAINS_NOT_TO_DISPLAY_TAG(Verdict.FALSE, "TagSet contains notToDisplay tag"),
	FORCE_INDEX_BY_INDEXABLE_BLOCK_TRUE(Verdict.FINAL_TRUE, "matching rule defining an <indexable> block statically set to true."),
	FORCE_INDEX_BY_INDEXABLE_BLOCK_FALSE(Verdict.FALSE, "matching rule defining an <indexable> block statically set to false."),
	CONTAINS_NON_INDEXABLE_TAG(Verdict.FALSE, "tag set contains tag with an non-indexable tag type"),
	WINDOW_PAGE(Verdict.FINAL_TRUE, "Window page"),
	INADEQUATE_ITEM_COUNT(Verdict.FALSE, "Too few items after applying imageGrouping."),
	HIGH_BOUNCE_RATE(Verdict.FALSE, "Tag set found in list of exceedingly high bounce rate URL."),
	TRAFFIC_REINDEXED_LESSTHENXPAGEIMPRESSIONSPAGEONELAST35DAYS(
		Verdict.FALSE,
		"traffic dependent indexation: tagSet is currently not on index and had less than X (tag_set_indexation.minimum_page_impressions_page_one_last_35_days_threshold) page impressions on page one in the last 35 days."
	),
	LESS_THAN_MIN_ITEM_COUNT(Verdict.FALSE, "less or equal than min item count"),
	MIN_SHOP_COUNT(Verdict.FALSE, "page has less than X different shops on first page"),
	SKIPPED_TRAFFIC_RULES_DUE_TO_PURE_STYLE_TAG_URL(Verdict.TRUE, "skipped traffic rules due to pure style tag url."),
	TRAFFIC_WELPENSCHUTZ(Verdict.TRUE, "tag set is protected by Welpenschutz."),
	REASON_TRAFFIC_ATLEASTXPAGEIMPRESSIONSPAGEONELAST35DAYS(
		Verdict.TRUE,
		"had at least X (tag_set_indexation.minimum_page_impressions_page_one_last_35_days_threshold) page impressions on page one in last 35 days."
	),
	REASON_TRAFFIC_REINDEXED_ATLEASTXPAGEIMPRESSIONSPAGEONELAST35DAYS(
		Verdict.TRUE,
		"traffic dependent indexation: tagSet is currently not on index, but had at least X (tag_set_indexation.minimum_page_impressions_page_one_last_35_days_threshold) page impressions on page one in the last 35 days."
	),
	REASON_TRAFFIC_ATLEAST200PAGEIMPRESSIONSPAGEONELAST365DAYS(Verdict.TRUE, "had at least 200 page impressions on page one in last 365 days."),
	REASON_TRAFFIC_ATLEAST10SEOLANDINGSINLAST365DAYS(Verdict.TRUE, "had at least 10 seo landings in last 365 days."),
	REASON_TRAFFIC_DEINDEXATION(Verdict.FALSE, "could not match any traffic rule and will therefore go off index."),
	REASON_TRAFFIC_REINDEXED_LESSTHENXPAGEIMPRESSIONSPAGEONELAST35DAYS(Verdict.FALSE,
		"traffic dependent indexation: tagSet is currently not on index and had less than X (tag_set_indexation.minimum_page_impressions_page_one_last_35_days_threshold) page impressions on page one in the last 35 days."),
	NOT_EXISTS(Verdict.FALSE, "Does not exist."),
	REASON_CANONICAL(Verdict.FALSE, "referencing merchandise instance has a canonical tag id."),
	UNKNOWN(Verdict.TRUE,"No rules applied to this tag")

}
