# OWASP: A06 Vulnerable and outdated components

This project demonstrates a vulnerability with the UserDetailsManager and PasswordEncoder
used by Spring Security in the class WebSecurityConfig.

## Vulnerability

The VulnerableUserDetailsManager logs each user with plain-text password for each login request. If any attacker gets
access to the log files he would have access to all username and password combinations.

The VulnerablePasswordEncoder doesn't encode anything and leaves the password plain-text.

## Countermeasure

In order to fix this vulnerability you have to change the usage of the VulnerableUserDetailsManager to
InMemoryUserDetailsManager and the VulnerablePasswordEncoder to BCryptPasswordEncoder in the WebSecurityConfig class.
Both lines are marked with a comment.

Additionally, you can add the [OWASP Dependency Check](https://plugins.gradle.org/plugin/org.owasp.dependencycheck) in order to verify each dependency.
You can run this gradle task to check if any dependency have any known vulnerability.

© Ian Raschle & Norwin Schäfer

