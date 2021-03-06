package pl.edu.pw.elka.sagwedt.crawler;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.jsoup.nodes.Document;

import com.google.common.collect.Lists;

public class OtodomCrawler extends AbstractCrawler{
    private static final String DOMAIN = "https://www.otodom.pl";
    private static final String FIRST_PAGE_HREF = DOMAIN + "/wynajem/mieszkanie/warszawa/";
    private static final String NEXT_PAGE_SELECTOR = ".listing .pager .pager-next a";
    private static final String OFFER_LIST_SELECTOR = ".listing .offer-item";
    private static final String OFFER_TEXT_SELECTOR = ".article-offer .section-offer-text";
    private static final String OFFER_PARAMS_SELECTOR = ".article-offer .section-offer-params";
    private static final String HREF = "href";
    private static final String DATA_URL = "data-url";

    @Override
    protected String getFirstPageHref()
    {
        return FIRST_PAGE_HREF;
    }

    @Override
    protected String getNextPageHref(final Document currentPage)
    {
        return currentPage.selectFirst(NEXT_PAGE_SELECTOR).attr(HREF);
    }

    @Override
    protected List<String> getOfferLinkList(final Document currentPage)
    {
        return currentPage.select(OFFER_LIST_SELECTOR).stream()
            .map(e -> e.attr(DATA_URL))
            .collect(toList());
    }

    @Override
    protected List<String> getOfferContentSelectors()
    {
        return Lists.newArrayList(
                OFFER_TEXT_SELECTOR,
                OFFER_PARAMS_SELECTOR);
    }
}
