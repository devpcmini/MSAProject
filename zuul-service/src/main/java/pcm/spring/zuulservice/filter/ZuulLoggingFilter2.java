package pcm.spring.zuulservice.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class ZuulLoggingFilter2 extends ZuulFilter{

	Logger log = LoggerFactory.getLogger(ZuulLoggingFilter2.class);
	//쓰거나 쓰지않거나
	@Override
	public boolean shouldFilter() {
		return true;
	}

	//어떠한 동작하는지
	@Override
	public Object run() throws ZuulException {
		log.info("*********************** post printing logs: ");
		
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		log.info("*********************** post printing logs: " + request.getRequestURI());
		return null;
	}

	//사전 필터인지 사후 필터인지, *사후 필터
	@Override
	public String filterType() {
		return "post";
	}

	//순서
	@Override
	public int filterOrder() {
		return 1;
	}

}
