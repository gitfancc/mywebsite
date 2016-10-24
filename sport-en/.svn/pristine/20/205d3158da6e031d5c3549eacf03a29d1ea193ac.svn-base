package com.appscomm.sport.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appscomm.sport.model.VersionCheckVO;

import com.appscomm.sport.dao.ClientVersionDao;
import com.appscomm.sport.dao.CommonDao;
import com.appscomm.sport.model.ClientVersionVO;
import com.appscomm.sport.service.ClientVersionService;

@Service("clientVersionService")
public class ClientVersionServiceImpl implements ClientVersionService {
	@Autowired
	private ClientVersionDao clientVersionDao;
	@Autowired
	private CommonDao commonDao;

	
	@Override
	public Integer addClientVersion(ClientVersionVO version) {
		ClientVersionVO clientVersion = this.clientVersionDao.queryClientVersion(version);
		if(clientVersion==null){
			return this.clientVersionDao.insertClientVersion(version);
		}else {
			version.setSid(clientVersion.getSid());
			version.setPreviousVersion(clientVersion.getInstallVersion());
			this.clientVersionDao.updateClientVersion(version);
			return version.getSid();
		}
	}

	@Override
	public ClientVersionVO getClientVersion(ClientVersionVO version) {
		ClientVersionVO clientVersion =  this.clientVersionDao.queryClientVersion(version);
		VersionCheckVO globalVersion  = this.commonDao.qryVersion(version.getClientName(), version.getClientType());
		if(clientVersion!=null){
			if(globalVersion!=null){
				clientVersion.setNewestVersion(globalVersion.getVersion());
				clientVersion.setUpdateUrl(globalVersion.getUpdateurl());
				clientVersion.setUpdateMessage(globalVersion.getUpdatemessage());
			}
		}
		return clientVersion;
	}

}
