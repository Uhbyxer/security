package com.acme.springsecurity.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by tarashrynchuk on 4/27/19.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Hello {
	private String greeting;
}
