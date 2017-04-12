# commons-locale
Commons Locale Project

# Usage
A LocaleSpace holds multiple Locales, which can be added through LocaleSpace#install, and retrieved with LocaleSpace#getLocale.

Locale's contain a code - the language code of the Locale (eg. en_US), and a Map of messageCodes (String) to messages (Message), which can be retrieved through Locale#getMessage(String code)

The text of a Message can be obtained with Message#getText, which will return a String value.
