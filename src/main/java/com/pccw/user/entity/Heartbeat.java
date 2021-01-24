package com.pccw.user.entity;

import lombok.Data;

@Data
public class Heartbeat {
	private String version;
	private String releasedAt;
}
