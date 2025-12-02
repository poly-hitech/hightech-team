package com.example.test;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RequestLogFilter extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest req, @NonNull HttpServletResponse res,
			@NonNull FilterChain chain) throws ServletException, IOException {

		long start = System.currentTimeMillis();
		String method = req.getMethod();
		String uri = req.getRequestURI();
		String qs = req.getQueryString();
		String full = qs == null ? uri : uri + "?" + qs;

		try {
			chain.doFilter(req, res);
		} finally {
			long took = System.currentTimeMillis() - start;
			int status = res.getStatus();
			log.info("{} {} -> {} ({} ms)", method, full, status, took);
		}
	}
}
