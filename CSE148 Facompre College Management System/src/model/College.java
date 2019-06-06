package model;

public class College {
	private PersonBag personBag;
	private TextbookBag textbookBag;
	private ClassroomBag classroomBag;
	private CourseBag courseBag;
	
	public College() {
		personBag = getPersonBag();
		//textbookBag = getTextbookBag();
	}
	
	public College(int maxPerson, int maxTextbook, int maxRoom, int maxCourse) {
		personBag = new PersonBag(maxPerson);
		textbookBag = new TextbookBag(maxTextbook);
		classroomBag = new ClassroomBag(maxRoom);
		courseBag = new CourseBag(maxCourse);
	}

	public PersonBag getPersonBag() {
		return personBag;
	}

	public void setPersonBag(PersonBag personBag) {
		this.personBag = personBag;
	}

	public TextbookBag getTextbookBag() {
		return textbookBag;
	}

	public void setTextbookBag(TextbookBag textbookBag) {
		this.textbookBag = textbookBag;
	}

	public ClassroomBag getClassroomBag() {
		return classroomBag;
	}

	public void setClassroomBag(ClassroomBag classroomBag) {
		this.classroomBag = classroomBag;
	}

	public CourseBag getCourseBag() {
		return courseBag;
	}

	public void setCourseBag(CourseBag courseBag) {
		this.courseBag = courseBag;
	}
}