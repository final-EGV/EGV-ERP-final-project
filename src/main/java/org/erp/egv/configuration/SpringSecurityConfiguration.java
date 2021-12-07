package org.erp.egv.configuration;

import org.erp.egv.employee.model.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity			// 설정 파일이면서 시큐리티 설정
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter{

	private EmployeeService employeeService;
	
	@Autowired
	public SpringSecurityConfiguration(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	/* 1. 암호화 처리를 위한 PasswordEncoder를 bean으로 설정 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/* 2. 시큐리티 설정을 무시할 정적 리소스들을 등록 (WebSecurityConfigurerAdapter의 메소드 오버라이딩(상속))*/
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/stamp-img/**");
	}
	
	/* 3. Http요청에 대한 권한 설정 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		/* csrf : 토큰 위조 공격을 막기 위한 것(default가 'on'인 상태) */
		http.csrf().disable()					// 구현의 편리를 위해 disable로 함
			.authorizeRequests()				// 요청에 대한 권한 체크를 어떻게 할 것인지(더 넓은 범위를 아래에 배치해야 함)
				.antMatchers("/emp/login").permitAll()					// 로그인은 전부 허용
				.antMatchers("/emp/pwreset").permitAll()				
				.antMatchers("/emp/findid").permitAll()
				.antMatchers("/emp/list").hasRole("인사담당자")
                .antMatchers("/emp/outEmpList").hasRole("인사담당자")
                .antMatchers("/emp/dept").hasRole("인사담당자")
                .antMatchers("/emp/rank").hasRole("인사담당자")
                .antMatchers("/emp/work").hasRole("인사담당자")
                .antMatchers("/emp/Leave").hasRole("인사담당자")
                .antMatchers("/emp/parttime/parttimeSchedule").hasRole("인사담당자")
                .antMatchers("/emp/salary/salary").hasRole("인사담당자")
                .antMatchers("/emp/salary/severancePay").hasRole("인사담당자")
                .antMatchers("/emp/salary/bankBook").hasRole("인사담당자")
                .antMatchers("/theater/**/details").hasRole("영화담당자")
                .antMatchers("/theater/**/regist").hasRole("영화담당자")
                .antMatchers("/theater/**/delete").hasRole("영화담당자")
                .antMatchers("/official/write").hasAnyRole("인사담당자","영화담당자")
                .antMatchers("/official/delete").hasAnyRole("인사담당자","영화담당자")
				.antMatchers("/**").authenticated()					// 인증된 사용자만 모든 접속에 허용
//				.antMatchers(HttpMethod.GET, "/emp/**").hasRole("Admin")
//				.antMatchers(HttpMethod.POST, "/").hasRole("a")
			.and()
			    .formLogin()												// 로그인 form을 따로 이용해 로그인 처리를 할 것이다.
			    .loginPage("/emp/login")									// 기본적으로 스프링 시큐리티에서 제공하는 로그인 화면 외에 로그인 화면을 따로 적용할 것이다.(권한이 획득되지 않아 로그인이 필요한 상황에도 사용할 수 있게 함)
			    .successForwardUrl("/")
			    .failureUrl("/emp/login?error=fail")
			.and()
			    .logout()													// 로그아웃 설정
			    .logoutRequestMatcher(new AntPathRequestMatcher("/emp/logout"))	// 로그아웃 시 요	청 경로
			    .deleteCookies("JSESSIONID")								// 쿠키 제거
			    .invalidateHttpSession(true)								// Session정보 무효화
			    .logoutSuccessUrl("/")			
			.and()
				.exceptionHandling()										// exception 핸들링 설정
				.accessDeniedPage("/common/denied")						// 접근 거부시 경로 설정
			.and()
				.sessionManagement()
				.invalidSessionUrl("/emp/sign/")
				.maximumSessions(1)
				.expiredUrl("/emp/login?out=out");
	}
	
	/* 4. 권한을 획득할 때 인증할 비지니스 로직(DB에서 조회하는 로직)이 어떤 것인지 등록 (Controller없이 어떤 비지니스 로직을 사용할지만 설정하면 된다.) */
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(employeeService).passwordEncoder(passwordEncoder());		// 암호화 방식도 등록 passwordEncoder()
	}
}


