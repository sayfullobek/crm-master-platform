package uTeamCrm.Crmmasterplatform.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.id.UUIDGenerator;
import org.hibernate.type.TimeType;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import uTeamCrm.Crmmasterplatform.Repository.*;
import uTeamCrm.Crmmasterplatform.entity.*;
import uTeamCrm.Crmmasterplatform.entity.enums.DayType;
import uTeamCrm.Crmmasterplatform.entity.enums.RoleName;
import uTeamCrm.Crmmasterplatform.entity.enums.WeekDayName;
import uTeamCrm.Crmmasterplatform.pyload.*;

import java.time.Instant;
import java.util.*;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepo groupRepo;

    private final RoomRepo roomRepo;

    private final CourseRepo courseRepo;

    private final WeekDayRepo weekDayRepo;

    private final AuthRepository authRepository;

    private final AcademyRepo academyRepo;

    private final StudentDailyStatisticsRepo studentDailyStatisticsRepo;

    private final MonthlyStatisticsRepo monthlyStatisticsRepo;

    private final AllStaticForPupilRepo allStaticForPupilRepo;

    public List<Group> getTeacherGroups(UUID id) {
        List<Group> groups = new ArrayList<>();
        for (Group group : groupRepo.findAll()) {
            if (group.getTeacher().getId().equals(id)) {
                Group build = Group.builder()
                        .pupils(group.getPupils())
                        .teacher(group.getTeacher())
                        .active(group.isActive())
                        .weekDays(group.getWeekDays())
                        .course(group.getCourse())
                        .room(group.getRoom())
                        .build();
                groups.add(build);
            }
        }
        return groups;
    }

    public ApiResponse createGroup(ReqGroup reqGroup) {
//        Academy getAcademy = academyRepo.findById(reqGroup.getAcademyId()).orElseThrow(() -> new ResourceNotFoundException("getAcademy"));
//        for (Group group : getAcademy.getGroups()) {
//            if (group.getCourse().getUzName().equals(reqGroup.getName())){
//                return new ApiResponse("bunday gruh mavjud", false);
//            }
//        }
        if (!groupRepo.existsGroupsByNameEqualsIgnoreCase(reqGroup.getName())) {
            Course getCourse = courseRepo.findById(reqGroup.getCourseId()).orElseThrow(() -> new ResourceNotFoundException("getCourse"));
            Room getRoom = roomRepo.findById(reqGroup.getRoomId()).orElseThrow(() -> new ResourceNotFoundException("getRoom"));
            User user = authRepository.findById(reqGroup.getTeacher()).orElseThrow(() -> new ResourceNotFoundException("getTeacher"));
            List<WeekDay> weekDays = new ArrayList<>();
            for (SelectDto weekday : reqGroup.getWeekdays()) {
                WeekDay getWeekDay = weekDayRepo.findById(weekday.getValue()).orElseThrow(() -> new ResourceNotFoundException("getWeekDay"));
                weekDays.add(getWeekDay);
            }
            List<WeekDay> weekDays1 = new ArrayList<>();
            weekDays1.add(weekDayRepo.findById(1).get());
            weekDays1.add(weekDayRepo.findById(3).get());
            weekDays1.add(weekDayRepo.findById(5).get());
            List<WeekDay> weekDays2 = new ArrayList<>();
            weekDays2.add(weekDayRepo.findById(2).get());
            weekDays2.add(weekDayRepo.findById(4).get());
            weekDays2.add(weekDayRepo.findById(6).get());
            for (Group group : groupRepo.findAll()) {
                if (group.isActive()) {
                    if (group.getTimeType().equals(reqGroup.getTimeType() == 1 ? DayType.COUPLE : reqGroup.getTimeType() == 2 ? DayType.ODD : DayType.OTHER)) {
//                        if (group.getTime().split("&&")[0].equals(s) || group.getTime().split("&&")[1].equals(s2)) {
                        if (group.getRoom().equals(getRoom)) {
                            return new ApiResponse("uzur bunday vaqtda gruh mavjud", true);
                        }
//                        }
                    }
                }
            }
            Group build = Group.builder()
                    .name(reqGroup.getName())
                    .room(getRoom)
                    .course(getCourse)
                    .active(true)
                    .teacher(user.getRole().getRoleName().equals(RoleName.TEACHER) ? user : null)
                    .time(reqGroup.getTime())
                    .timeType(reqGroup.getTimeType() == 1 ? DayType.COUPLE : reqGroup.getTimeType() == 2 ? DayType.ODD : DayType.OTHER)
                    .weekDays(reqGroup.getTimeType() == 1 ? weekDays1 : reqGroup.getTimeType() == 2 ? weekDays2 : weekDays)
                    .build();
            groupRepo.save(build);
            return new ApiResponse("gruh qo'shildi", true);
        }
        return new ApiResponse("ushbu gruh allaqachon mavjud", false);
    }

    public ApiResponse groupDisabled(UUID groupId) {
        Group getGroup = groupRepo.findById(groupId).orElseThrow(() -> new ResourceNotFoundException("getGroup"));
        getGroup.setActive(false);
        groupRepo.save(getGroup);
        return new ApiResponse("group disabledlashtirldi", true);
    }

    public ApiResponse addPupilToGroup(UUID groupId, List<PupilSelectDto> pupils) {
        Group getGroup = groupRepo.findById(groupId).orElseThrow(() -> new ResourceNotFoundException("getGroup"));
        List<User> users = new ArrayList<>();
        for (PupilSelectDto pupil : pupils) {
            User user = authRepository.findById(pupil.getValue()).orElseThrow(() -> new ResourceNotFoundException("getPupil"));
            for (Course cours : user.getCourses()) {
                if (cours.equals(getGroup.getCourse())){
                    users.add(user);
                    TotalLessonAndDailyFee dailyFee = getDailyFee(getGroup, getGroup.getCourse());
                    AllStatisticForPupil allStatisticForPupilByUserId = allStaticForPupilRepo.findAllStatisticForPupilByUserId(pupil.getValue());
                    if (allStatisticForPupilByUserId == null) {
                        AllStatisticForPupil build = AllStatisticForPupil.builder().user(user).dailyFee(dailyFee.getDailyFee()).allCost(getGroup.getCourse().getCoursePrice()).build();
                        allStaticForPupilRepo.save(build);
                    } else {
                        allStatisticForPupilByUserId.setAllCost(allStatisticForPupilByUserId.getAllCost() + getGroup.getCourse().getCoursePrice());
                        allStatisticForPupilByUserId.setDailyFee(allStatisticForPupilByUserId.getDailyFee() + dailyFee.getDailyFee());
                        allStaticForPupilRepo.save(allStatisticForPupilByUserId);
                    }
                }
            }
        }
        getGroup.getPupils().addAll(users);
        groupRepo.save(getGroup);
        return new ApiResponse("gruhga o'quvchilar  saqlandi", true);
    }


    public ApiResponse removeFromGroup(UUID pupilId, UUID groupId) {
        User user = authRepository.findById(pupilId).orElseThrow(() -> new ResourceNotFoundException("getPupil"));
        Group getGroup = groupRepo.findById(groupId).orElseThrow(() -> new ResourceNotFoundException("getGroup"));
        getGroup.getPupils().remove(user);
        groupRepo.save(getGroup);
        return new ApiResponse("Gruhdan o'quvchi olib tashlandi", true);
    }

    public TotalLessonAndDailyFee getDailyFee(Group getGroup, Course course) {
        double dailyFee = 0;
        Calendar instance = Calendar.getInstance();
        int size = getGroup.getWeekDays().size();
        int actualMaximum = instance.getActualMaximum(Calendar.DAY_OF_MONTH);
        int hafta = actualMaximum / 7;
        int qoldiqKun = actualMaximum % 7;
        int darslarSoni = size * hafta;
        int qoldiqDars = qoldiqKun == size ? 2 : size / qoldiqKun;
        int jamiDarslarSoni = darslarSoni + qoldiqDars;
        dailyFee = dailyFee + course.getCoursePrice() / jamiDarslarSoni;
        return new TotalLessonAndDailyFee(dailyFee, jamiDarslarSoni);
    }
}
