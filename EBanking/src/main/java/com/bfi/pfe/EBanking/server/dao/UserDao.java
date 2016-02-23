package com.bfi.pfe.EBanking.server.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bfi.pfe.EBanking.shared.model.Account;
import com.bfi.pfe.EBanking.shared.model.Benificiary;
import com.bfi.pfe.EBanking.shared.model.User;
import com.bfi.pfe.EBanking.shared.model.dto.AccountDTO;
import com.bfi.pfe.EBanking.shared.model.dto.UserDTO;

@Repository
@Transactional
public class UserDao {

	@Autowired
	SessionFactory sessionFactory;

	protected Session getSession() {

		return sessionFactory.getCurrentSession();
	}

	public UserDTO get(UserDTO user) {
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.and(
				Restrictions.eq("login", user.getLogin()),
				Restrictions.eq("password", user.getPassword())));
		User userToReturn = (User) criteria.uniqueResult();
		return createUserDto(userToReturn);
	}

	public UserDTO getUserById(Long id) {
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("id", id));
		User userToReturn = (User) criteria.uniqueResult();
		return createUserDto(userToReturn);
	}

	private UserDTO createUserDto(User userToReturn) {
		// TODO Auto-generated method stub
		List<Account> accounts = userToReturn.getAccounts();
		List<AccountDTO> accountsDTO = new ArrayList<AccountDTO>(
				accounts != null ? accounts.size() : 0);
		if (accounts != null) {
			for (Account account : accounts) {
				accountsDTO.add(new AccountDTO(account.getId(), account
						.getRib(), account.getOpeningDate(),
						account.getSolde(), account.getType(), null, null,
						new UserDTO(), null, null));
			}
		}
		return new UserDTO(userToReturn.getId(), userToReturn.getLogin(),
				userToReturn.getPassword(), userToReturn.getFullName(),
				accountsDTO);
	}

	@SuppressWarnings("unchecked")
	public List<AccountDTO> getAccounts(UserDTO user) {
		List<Account> acoountsList = new ArrayList<Account>(getSession()
				.createQuery("from Account where id_user=" + user.getId())
				.list());

		List<AccountDTO> accountDTOs = new ArrayList<AccountDTO>(
				acoountsList != null ? acoountsList.size() : 0);
		if (acoountsList != null) {
			for (Account account : acoountsList) {

				accountDTOs.add(new AccountDTO(account.getId(), account
						.getRib(), account.getOpeningDate(),
						account.getSolde(), account.getType(), null, null,
						new UserDTO(), null, null));
			}
		}
		return accountDTOs;

	}

	public void createBenificiary(Benificiary benificiary) {
		getSession().save(benificiary);
	}

}
