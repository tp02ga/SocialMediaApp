package net.javavideotutorials.example.form;

public class FriendshipForm
{
	private Long pendingFriendshipUserId;
	private String action;

	public Long getPendingFriendshipUserId()
	{
		return pendingFriendshipUserId;
	}

	public void setPendingFriendshipUserId(Long pendingFriendshipUserId)
	{
		this.pendingFriendshipUserId = pendingFriendshipUserId;
	}

	public String getAction()
	{
		return action;
	}

	public void setAction(String action)
	{
		this.action = action;
	}
}
