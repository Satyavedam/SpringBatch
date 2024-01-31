package springBatch.example.SpringBatchdemo.entity;

public class Manager {


	@Entity
	@Table(name="ManagerInfo")
	public class Manager {
		@Id
		@Column(name ="ManagerId")
		private int Id;
		
		
		@Column(name = "ManagerName")
		private String name;
		
		@Column (name= "Email")
		private String email;

		public Manager() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Manager(int id, String name, String email) {
			super();
			Id = id;
			this.name = name;
			this.email = email;
		}

		public int getId() {
			return Id;
		}

		public void setId(int id) {
			Id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
		
	}


}
