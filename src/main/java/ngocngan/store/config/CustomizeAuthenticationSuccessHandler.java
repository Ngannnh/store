//package ngocngan.store.config;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * @author ngan nnh on 5/27/2019
// * @project store
// */
//
//public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
//            Authentication authentication) throws IOException, ServletException {
//        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
//        for(GrantedAuthority grantedAuthority : authentication.getAuthorities()){
//            if("ADMIN".equals(grantedAuthority.getAuthority())){
//                httpServletResponse.sendRedirect("/admin");
//            }
//            if("USER".equals(grantedAuthority.getAuthority())){
//                httpServletResponse.sendRedirect("/admin");
//            }
//        }
//    }
//}
