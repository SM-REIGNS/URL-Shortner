package com.shubham.urlshortenerapi.service;

import com.shubham.urlshortenerapi.dto.UrlLongRequest;
import com.shubham.urlshortenerapi.entity.Url;
import com.shubham.urlshortenerapi.repository.UrlRepository;
import com.shubham.urlshortenerapi.service.BaseConversion;
import com.shubham.urlshortenerapi.service.UrlService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UrlServiceTest {

    @Mock
    UrlRepository mockUrlRepository;

    @Mock
    BaseConversion mockBaseConversion;

    @InjectMocks
    UrlService urlService;

    @Test
    public void convertToShortUrlTest() {
        var url = new Url();
        url.setLongUrl("https://github.com/SM-REIGNS/URL-Shortner");
        url.setId(5);

        when(mockUrlRepository.save(any(Url.class))).thenReturn(url);
        when(mockBaseConversion.encode(url.getId())).thenReturn("f");

        var urlRequest = new UrlLongRequest();
        urlRequest.setLongUrl("https://github.com/SM-REIGNS/URL-Shortner");

        assertEquals("f", urlService.convertToShortUrl(urlRequest));
    }

    @Test
    public void getOriginalUrlTest() {
        when(mockBaseConversion.decode("h")).thenReturn((long) 7);

        var url = new Url();
        url.setLongUrl("https://github.com/SM-REIGNS/URL-Shortner");
        url.setId(7);

        when(mockUrlRepository.findById((long) 7)).thenReturn(java.util.Optional.of(url));
        assertEquals("https://github.com/SM-REIGNS/URL-Shortner", urlService.getOriginalUrl("h"));

    }
}
