package by.sam_solutions.kazak.social_network.controllers;

public interface WebConstants {

  String REDIRECT_PREFIX = "redirect:";
  String HOMEPAGE_URN = "/";
  String ID_PREFIX = "/id";
  String MESSAGES = "/messages/";
  String EDIT_PROFILE = "/edit/profile";
  String EDIT_BASIC = "/edit/basic";
  String ADMIN_PROFILES = "/admin/profiles/";
  String REDIRECT_TO_HOMEPAGE_URN = REDIRECT_PREFIX + HOMEPAGE_URN;
  String REDIRECT_TO_PROFILE = REDIRECT_PREFIX + ID_PREFIX;
  String REDIRECT_TO_ADMIN_PROFILES = REDIRECT_PREFIX + ADMIN_PROFILES;
  String REDIRECT_TO_EDIT_PROFILE = REDIRECT_PREFIX + EDIT_PROFILE;
  String REDIRECT_TO_EDIT_BASIC = REDIRECT_PREFIX + EDIT_BASIC;
  String REDIRECT_TO_MESSAGES = REDIRECT_PREFIX + MESSAGES;

}
