/**
  * This file is part of VoteBox.
  * 
  * VoteBox is free software: you can redistribute it and/or modify
  * it under the terms of the GNU General Public License version 3 as published by
  * the Free Software Foundation.
  * 
  * You should have received a copy of the GNU General Public License
  * along with VoteBox, found in the root of any distribution or
  * repository containing all or part of VoteBox.
  * 
  * THIS SOFTWARE IS PROVIDED BY WILLIAM MARSH RICE UNIVERSITY, HOUSTON,
  * TX AND IS PROVIDED 'AS IS' AND WITHOUT ANY EXPRESS, IMPLIED OR
  * STATUTORY WARRANTIES, INCLUDING, BUT NOT LIMITED TO, WARRANTIES OF
  * ACCURACY, COMPLETENESS, AND NONINFRINGEMENT.  THE SOFTWARE USER SHALL
  * INDEMNIFY, DEFEND AND HOLD HARMLESS RICE UNIVERSITY AND ITS FACULTY,
  * STAFF AND STUDENTS FROM ANY AND ALL CLAIMS, ACTIONS, DAMAGES, LOSSES,
  * LIABILITIES, COSTS AND EXPENSES, INCLUDING ATTORNEYS' FEES AND COURT
  * COSTS, DIRECTLY OR INDIRECTLY ARISING OUR OF OR IN CONNECTION WITH
  * ACCESS OR USE OF THE SOFTWARE.
 */

/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 1.3.29
 * 
 * This file is not intended to be easily readable and contains a number of 
 * coding conventions designed to improve portability and efficiency. Do not make
 * changes to this file unless you know what you are doing--modify the SWIG 
 * interface file instead. 
 * ----------------------------------------------------------------------------- */

/* -----------------------------------------------------------------------------
 *  This section contains generic SWIG labels for method/variable
 *  declarations/attributes, and other compiler dependent labels.
 * ----------------------------------------------------------------------------- */

/* template workaround for compilers that cannot correctly implement the C++ standard */
#ifndef SWIGTEMPLATEDISAMBIGUATOR
# if defined(__SUNPRO_CC)
#   if (__SUNPRO_CC <= 0x560)
#     define SWIGTEMPLATEDISAMBIGUATOR template
#   else
#     define SWIGTEMPLATEDISAMBIGUATOR 
#   endif
# else
#   define SWIGTEMPLATEDISAMBIGUATOR 
# endif
#endif

/* inline attribute */
#ifndef SWIGINLINE
# if defined(__cplusplus) || (defined(__GNUC__) && !defined(__STRICT_ANSI__))
#   define SWIGINLINE inline
# else
#   define SWIGINLINE
# endif
#endif

/* attribute recognised by some compilers to avoid 'unused' warnings */
#ifndef SWIGUNUSED
# if defined(__GNUC__)
#   if !(defined(__cplusplus)) || (__GNUC__ > 3 || (__GNUC__ == 3 && __GNUC_MINOR__ >= 4))
#     define SWIGUNUSED __attribute__ ((__unused__)) 
#   else
#     define SWIGUNUSED
#   endif
# elif defined(__ICC)
#   define SWIGUNUSED __attribute__ ((__unused__)) 
# else
#   define SWIGUNUSED 
# endif
#endif

#ifndef SWIGUNUSEDPARM
# ifdef __cplusplus
#   define SWIGUNUSEDPARM(p)
# else
#   define SWIGUNUSEDPARM(p) p SWIGUNUSED 
# endif
#endif

/* internal SWIG method */
#ifndef SWIGINTERN
# define SWIGINTERN static SWIGUNUSED
#endif

/* internal inline SWIG method */
#ifndef SWIGINTERNINLINE
# define SWIGINTERNINLINE SWIGINTERN SWIGINLINE
#endif

/* exporting methods */
#if (__GNUC__ >= 4) || (__GNUC__ == 3 && __GNUC_MINOR__ >= 4)
#  ifndef GCC_HASCLASSVISIBILITY
#    define GCC_HASCLASSVISIBILITY
#  endif
#endif

#ifndef SWIGEXPORT
# if defined(_WIN32) || defined(__WIN32__) || defined(__CYGWIN__)
#   if defined(STATIC_LINKED)
#     define SWIGEXPORT
#   else
#     define SWIGEXPORT __declspec(dllexport)
#   endif
# else
#   if defined(__GNUC__) && defined(GCC_HASCLASSVISIBILITY)
#     define SWIGEXPORT __attribute__ ((visibility("default")))
#   else
#     define SWIGEXPORT
#   endif
# endif
#endif

/* calling conventions for Windows */
#ifndef SWIGSTDCALL
# if defined(_WIN32) || defined(__WIN32__) || defined(__CYGWIN__)
#   define SWIGSTDCALL __stdcall
# else
#   define SWIGSTDCALL
# endif 
#endif

/* Deal with Microsoft's attempt at deprecating C standard runtime functions */
#if !defined(SWIG_NO_CRT_SECURE_NO_DEPRECATE) && defined(_MSC_VER)
# define _CRT_SECURE_NO_DEPRECATE
#endif


/* Fix for jlong on some versions of gcc on Windows */
#if defined(__GNUC__) && !defined(__INTELC__)
  typedef long long __int64;
#endif

/* Fix for jlong on 64-bit x86 Solaris */
#if defined(__x86_64)
# ifdef _LP64
#   undef _LP64
# endif
#endif

#include <jni.h>
#include <stdlib.h>
#include <string.h>


/* Support for throwing Java exceptions */
typedef enum {
  SWIG_JavaOutOfMemoryError = 1, 
  SWIG_JavaIOException, 
  SWIG_JavaRuntimeException, 
  SWIG_JavaIndexOutOfBoundsException,
  SWIG_JavaArithmeticException,
  SWIG_JavaIllegalArgumentException,
  SWIG_JavaNullPointerException,
  SWIG_JavaDirectorPureVirtual,
  SWIG_JavaUnknownError
} SWIG_JavaExceptionCodes;

typedef struct {
  SWIG_JavaExceptionCodes code;
  const char *java_exception;
} SWIG_JavaExceptions_t;


static void SWIGUNUSED SWIG_JavaThrowException(JNIEnv *jenv, SWIG_JavaExceptionCodes code, const char *msg) {
  jclass excep;
  static const SWIG_JavaExceptions_t java_exceptions[] = {
    { SWIG_JavaOutOfMemoryError, "java/lang/OutOfMemoryError" },
    { SWIG_JavaIOException, "java/io/IOException" },
    { SWIG_JavaRuntimeException, "java/lang/RuntimeException" },
    { SWIG_JavaIndexOutOfBoundsException, "java/lang/IndexOutOfBoundsException" },
    { SWIG_JavaArithmeticException, "java/lang/ArithmeticException" },
    { SWIG_JavaIllegalArgumentException, "java/lang/IllegalArgumentException" },
    { SWIG_JavaNullPointerException, "java/lang/NullPointerException" },
    { SWIG_JavaDirectorPureVirtual, "java/lang/RuntimeException" },
    { SWIG_JavaUnknownError,  "java/lang/UnknownError" },
    { (SWIG_JavaExceptionCodes)0,  "java/lang/UnknownError" } };
  const SWIG_JavaExceptions_t *except_ptr = java_exceptions;

  while (except_ptr->code != code && except_ptr->code)
    except_ptr++;

  (*jenv)->ExceptionClear(jenv);
  excep = (*jenv)->FindClass(jenv, except_ptr->java_exception);
  if (excep)
    (*jenv)->ThrowNew(jenv, excep, msg);
}


/* Contract support */

#define SWIG_contract_assert(nullreturn, expr, msg) if (!(expr)) {SWIG_JavaThrowException(jenv, SWIG_JavaIllegalArgumentException, msg); return nullreturn; } else


				#include "vbWrapper.h"
			

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT jint JNICALL Java_votebox_sdl_SDLJNI_VBWRAPPER_1get(JNIEnv *jenv, jclass jcls) {
  jint jresult = 0 ;
  int result;
  
  (void)jenv;
  (void)jcls;
  result = (int) 1;
  
  jresult = (jint)result; 
  return jresult;
}


JNIEXPORT jint JNICALL Java_votebox_sdl_SDLJNI_vbSDL_1Init(JNIEnv *jenv, jclass jcls, jint jarg1) {
  jint jresult = 0 ;
  int arg1 ;
  int result;
  
  (void)jenv;
  (void)jcls;
  arg1 = (int)jarg1; 
  result = (int)vbSDL_Init(arg1);
  jresult = (jint)result; 
  return jresult;
}


JNIEXPORT void JNICALL Java_votebox_sdl_SDLJNI_vbSDL_1Quit(JNIEnv *jenv, jclass jcls) {
  (void)jenv;
  (void)jcls;
  vbSDL_Quit();
}


JNIEXPORT jlong JNICALL Java_votebox_sdl_SDLJNI_vbSDL_1SetVideoMode(JNIEnv *jenv, jclass jcls, jint jarg1, jint jarg2, jint jarg3, jint jarg4) {
  jlong jresult = 0 ;
  int arg1 ;
  int arg2 ;
  int arg3 ;
  int arg4 ;
  SDL_Surface *result = 0 ;
  
  (void)jenv;
  (void)jcls;
  arg1 = (int)jarg1; 
  arg2 = (int)jarg2; 
  arg3 = (int)jarg3; 
  arg4 = (int)jarg4; 
  result = (SDL_Surface *)vbSDL_SetVideoMode(arg1,arg2,arg3,arg4);
  *(SDL_Surface **)&jresult = result; 
  return jresult;
}


JNIEXPORT jlong JNICALL Java_votebox_sdl_SDLJNI_vbSDL_1LoadBMP(JNIEnv *jenv, jclass jcls, jstring jarg1) {
  jlong jresult = 0 ;
  char *arg1 = (char *) 0 ;
  SDL_Surface *result = 0 ;
  
  (void)jenv;
  (void)jcls;
  arg1 = 0;
  if (jarg1) {
    arg1 = (char *)(*jenv)->GetStringUTFChars(jenv, jarg1, 0);
    if (!arg1) return 0;
  }
  result = (SDL_Surface *)vbSDL_LoadBMP(arg1);
  *(SDL_Surface **)&jresult = result; 
  if (arg1) (*jenv)->ReleaseStringUTFChars(jenv, jarg1, arg1);
  return jresult;
}


JNIEXPORT jint JNICALL Java_votebox_sdl_SDLJNI_vbSDL_1SetColors(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jint jarg3, jint jarg4) {
  jint jresult = 0 ;
  SDL_Surface *arg1 = (SDL_Surface *) 0 ;
  SDL_Color *arg2 = (SDL_Color *) 0 ;
  int arg3 ;
  int arg4 ;
  int result;
  
  (void)jenv;
  (void)jcls;
  arg1 = *(SDL_Surface **)&jarg1; 
  arg2 = *(SDL_Color **)&jarg2; 
  arg3 = (int)jarg3; 
  arg4 = (int)jarg4; 
  result = (int)vbSDL_SetColors(arg1,arg2,arg3,arg4);
  jresult = (jint)result; 
  return jresult;
}


JNIEXPORT jint JNICALL Java_votebox_sdl_SDLJNI_vbSDL_1BlitSurface(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jlong jarg3, jlong jarg4) {
  jint jresult = 0 ;
  SDL_Surface *arg1 = (SDL_Surface *) 0 ;
  SDL_Rect *arg2 = (SDL_Rect *) 0 ;
  SDL_Surface *arg3 = (SDL_Surface *) 0 ;
  SDL_Rect *arg4 = (SDL_Rect *) 0 ;
  int result;
  
  (void)jenv;
  (void)jcls;
  arg1 = *(SDL_Surface **)&jarg1; 
  arg2 = *(SDL_Rect **)&jarg2; 
  arg3 = *(SDL_Surface **)&jarg3; 
  arg4 = *(SDL_Rect **)&jarg4; 
  result = (int)vbSDL_BlitSurface(arg1,arg2,arg3,arg4);
  jresult = (jint)result; 
  return jresult;
}


JNIEXPORT void JNICALL Java_votebox_sdl_SDLJNI_vbSDL_1UpdateRect(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jint jarg3, jint jarg4, jint jarg5) {
  SDL_Surface *arg1 = (SDL_Surface *) 0 ;
  int arg2 ;
  int arg3 ;
  int arg4 ;
  int arg5 ;
  
  (void)jenv;
  (void)jcls;
  arg1 = *(SDL_Surface **)&jarg1; 
  arg2 = (int)jarg2; 
  arg3 = (int)jarg3; 
  arg4 = (int)jarg4; 
  arg5 = (int)jarg5; 
  vbSDL_UpdateRect(arg1,arg2,arg3,arg4,arg5);
}


JNIEXPORT void JNICALL Java_votebox_sdl_SDLJNI_vbSDL_1FreeSurface(JNIEnv *jenv, jclass jcls, jlong jarg1) {
  SDL_Surface *arg1 = (SDL_Surface *) 0 ;
  
  (void)jenv;
  (void)jcls;
  arg1 = *(SDL_Surface **)&jarg1; 
  vbSDL_FreeSurface(arg1);
}


JNIEXPORT jint JNICALL Java_votebox_sdl_SDLJNI_vbSDL_1WaitEvent(JNIEnv *jenv, jclass jcls, jlong jarg1) {
  jint jresult = 0 ;
  SDL_Event *arg1 = (SDL_Event *) 0 ;
  int result;
  
  (void)jenv;
  (void)jcls;
  arg1 = *(SDL_Event **)&jarg1; 
  result = (int)vbSDL_WaitEvent(arg1);
  jresult = (jint)result; 
  return jresult;
}


JNIEXPORT void JNICALL Java_votebox_sdl_SDLJNI_vbSDL_1SetClipRect(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
  SDL_Surface *arg1 = (SDL_Surface *) 0 ;
  SDL_Rect *arg2 = (SDL_Rect *) 0 ;
  
  (void)jenv;
  (void)jcls;
  arg1 = *(SDL_Surface **)&jarg1; 
  arg2 = *(SDL_Rect **)&jarg2; 
  vbSDL_SetClipRect(arg1,arg2);
}


JNIEXPORT jint JNICALL Java_votebox_sdl_SDLJNI_vbSDL_1FillRect(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jint jarg3) {
  jint jresult = 0 ;
  SDL_Surface *arg1 = (SDL_Surface *) 0 ;
  SDL_Rect *arg2 = (SDL_Rect *) 0 ;
  int arg3 ;
  int result;
  
  (void)jenv;
  (void)jcls;
  arg1 = *(SDL_Surface **)&jarg1; 
  arg2 = *(SDL_Rect **)&jarg2; 
  arg3 = (int)jarg3; 
  result = (int)vbSDL_FillRect(arg1,arg2,arg3);
  jresult = (jint)result; 
  return jresult;
}


JNIEXPORT void JNICALL Java_votebox_sdl_SDLJNI_vbSDL_1GetClipRect(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2) {
  SDL_Surface *arg1 = (SDL_Surface *) 0 ;
  SDL_Rect *arg2 = (SDL_Rect *) 0 ;
  
  (void)jenv;
  (void)jcls;
  arg1 = *(SDL_Surface **)&jarg1; 
  arg2 = *(SDL_Rect **)&jarg2; 
  vbSDL_GetClipRect(arg1,arg2);
}


JNIEXPORT jlong JNICALL Java_votebox_sdl_SDLJNI_vbSDL_1ConvertSurface(JNIEnv *jenv, jclass jcls, jlong jarg1, jlong jarg2, jint jarg3) {
  jlong jresult = 0 ;
  SDL_Surface *arg1 = (SDL_Surface *) 0 ;
  SDL_PixelFormat *arg2 = (SDL_PixelFormat *) 0 ;
  int arg3 ;
  SDL_Surface *result = 0 ;
  
  (void)jenv;
  (void)jcls;
  arg1 = *(SDL_Surface **)&jarg1; 
  arg2 = *(SDL_PixelFormat **)&jarg2; 
  arg3 = (int)jarg3; 
  result = (SDL_Surface *)vbSDL_ConvertSurface(arg1,arg2,arg3);
  *(SDL_Surface **)&jresult = result; 
  return jresult;
}


JNIEXPORT jint JNICALL Java_votebox_sdl_SDLJNI_Enabled_1Elo_1Touchscreen(JNIEnv *jenv, jclass jcls, jstring jarg1) {
  jint jresult = 0 ;
  char *arg1 = (char *) 0 ;
  int result;
  
  (void)jenv;
  (void)jcls;
  arg1 = 0;
  if (jarg1) {
    arg1 = (char *)(*jenv)->GetStringUTFChars(jenv, jarg1, 0);
    if (!arg1) return 0;
  }
  result = (int)Enabled_Elo_Touchscreen(arg1);
  jresult = (jint)result; 
  if (arg1) (*jenv)->ReleaseStringUTFChars(jenv, jarg1, arg1);
  return jresult;
}


JNIEXPORT jint JNICALL Java_votebox_sdl_SDLJNI_Event_1Get_1Type(JNIEnv *jenv, jclass jcls, jlong jarg1) {
  jint jresult = 0 ;
  SDL_Event *arg1 = (SDL_Event *) 0 ;
  int result;
  
  (void)jenv;
  (void)jcls;
  arg1 = *(SDL_Event **)&jarg1; 
  result = (int)Event_Get_Type(arg1);
  jresult = (jint)result; 
  return jresult;
}


JNIEXPORT jint JNICALL Java_votebox_sdl_SDLJNI_Event_1Get_1Mouse_1X(JNIEnv *jenv, jclass jcls, jlong jarg1) {
  jint jresult = 0 ;
  SDL_Event *arg1 = (SDL_Event *) 0 ;
  int result;
  
  (void)jenv;
  (void)jcls;
  arg1 = *(SDL_Event **)&jarg1; 
  result = (int)Event_Get_Mouse_X(arg1);
  jresult = (jint)result; 
  return jresult;
}


JNIEXPORT jint JNICALL Java_votebox_sdl_SDLJNI_Event_1Get_1Mouse_1Y(JNIEnv *jenv, jclass jcls, jlong jarg1) {
  jint jresult = 0 ;
  SDL_Event *arg1 = (SDL_Event *) 0 ;
  int result;
  
  (void)jenv;
  (void)jcls;
  arg1 = *(SDL_Event **)&jarg1; 
  result = (int)Event_Get_Mouse_Y(arg1);
  jresult = (jint)result; 
  return jresult;
}


JNIEXPORT jint JNICALL Java_votebox_sdl_SDLJNI_Event_1Get_1Mouse_1Button(JNIEnv *jenv, jclass jcls, jlong jarg1) {
  jint jresult = 0 ;
  SDL_Event *arg1 = (SDL_Event *) 0 ;
  int result;
  
  (void)jenv;
  (void)jcls;
  arg1 = *(SDL_Event **)&jarg1; 
  result = (int)Event_Get_Mouse_Button(arg1);
  jresult = (jint)result; 
  return jresult;
}


JNIEXPORT jint JNICALL Java_votebox_sdl_SDLJNI_Event_1Get_1Key(JNIEnv *jenv, jclass jcls, jlong jarg1) {
  jint jresult = 0 ;
  SDL_Event *arg1 = (SDL_Event *) 0 ;
  int result;
  
  (void)jenv;
  (void)jcls;
  arg1 = *(SDL_Event **)&jarg1; 
  result = (int)Event_Get_Key(arg1);
  jresult = (jint)result; 
  return jresult;
}


JNIEXPORT void JNICALL Java_votebox_sdl_SDLJNI_Rect_1Set(JNIEnv *jenv, jclass jcls, jlong jarg1, jint jarg2, jint jarg3, jint jarg4, jint jarg5) {
  SDL_Rect *arg1 = (SDL_Rect *) 0 ;
  int arg2 ;
  int arg3 ;
  int arg4 ;
  int arg5 ;
  
  (void)jenv;
  (void)jcls;
  arg1 = *(SDL_Rect **)&jarg1; 
  arg2 = (int)jarg2; 
  arg3 = (int)jarg3; 
  arg4 = (int)jarg4; 
  arg5 = (int)jarg5; 
  Rect_Set(arg1,arg2,arg3,arg4,arg5);
}


JNIEXPORT jint JNICALL Java_votebox_sdl_SDLJNI_Rect_1Get_1X(JNIEnv *jenv, jclass jcls, jlong jarg1) {
  jint jresult = 0 ;
  SDL_Rect *arg1 = (SDL_Rect *) 0 ;
  int result;
  
  (void)jenv;
  (void)jcls;
  arg1 = *(SDL_Rect **)&jarg1; 
  result = (int)Rect_Get_X(arg1);
  jresult = (jint)result; 
  return jresult;
}


JNIEXPORT jint JNICALL Java_votebox_sdl_SDLJNI_Rect_1Get_1Y(JNIEnv *jenv, jclass jcls, jlong jarg1) {
  jint jresult = 0 ;
  SDL_Rect *arg1 = (SDL_Rect *) 0 ;
  int result;
  
  (void)jenv;
  (void)jcls;
  arg1 = *(SDL_Rect **)&jarg1; 
  result = (int)Rect_Get_Y(arg1);
  jresult = (jint)result; 
  return jresult;
}


JNIEXPORT jint JNICALL Java_votebox_sdl_SDLJNI_Rect_1Get_1Width(JNIEnv *jenv, jclass jcls, jlong jarg1) {
  jint jresult = 0 ;
  SDL_Rect *arg1 = (SDL_Rect *) 0 ;
  int result;
  
  (void)jenv;
  (void)jcls;
  arg1 = *(SDL_Rect **)&jarg1; 
  result = (int)Rect_Get_Width(arg1);
  jresult = (jint)result; 
  return jresult;
}


JNIEXPORT jint JNICALL Java_votebox_sdl_SDLJNI_Rect_1Get_1Height(JNIEnv *jenv, jclass jcls, jlong jarg1) {
  jint jresult = 0 ;
  SDL_Rect *arg1 = (SDL_Rect *) 0 ;
  int result;
  
  (void)jenv;
  (void)jcls;
  arg1 = *(SDL_Rect **)&jarg1; 
  result = (int)Rect_Get_Height(arg1);
  jresult = (jint)result; 
  return jresult;
}


JNIEXPORT jint JNICALL Java_votebox_sdl_SDLJNI_Surface_1Set_1Pixel(JNIEnv *jenv, jclass jcls, jint jarg1, jint jarg2, jlong jarg3, jint jarg4, jint jarg5, jint jarg6, jint jarg7) {
  jint jresult = 0 ;
  int arg1 ;
  int arg2 ;
  SDL_Surface *arg3 = (SDL_Surface *) 0 ;
  int arg4 ;
  int arg5 ;
  int arg6 ;
  int arg7 ;
  int result;
  
  (void)jenv;
  (void)jcls;
  arg1 = (int)jarg1; 
  arg2 = (int)jarg2; 
  arg3 = *(SDL_Surface **)&jarg3; 
  arg4 = (int)jarg4; 
  arg5 = (int)jarg5; 
  arg6 = (int)jarg6; 
  arg7 = (int)jarg7; 
  result = (int)Surface_Set_Pixel(arg1,arg2,arg3,arg4,arg5,arg6,arg7);
  jresult = (jint)result; 
  return jresult;
}


JNIEXPORT jint JNICALL Java_votebox_sdl_SDLJNI_Surface_1Get_1Pixel_1R(JNIEnv *jenv, jclass jcls, jint jarg1, jint jarg2, jlong jarg3) {
  jint jresult = 0 ;
  int arg1 ;
  int arg2 ;
  SDL_Surface *arg3 = (SDL_Surface *) 0 ;
  int result;
  
  (void)jenv;
  (void)jcls;
  arg1 = (int)jarg1; 
  arg2 = (int)jarg2; 
  arg3 = *(SDL_Surface **)&jarg3; 
  result = (int)Surface_Get_Pixel_R(arg1,arg2,arg3);
  jresult = (jint)result; 
  return jresult;
}


JNIEXPORT jint JNICALL Java_votebox_sdl_SDLJNI_Surface_1Get_1Pixel_1G(JNIEnv *jenv, jclass jcls, jint jarg1, jint jarg2, jlong jarg3) {
  jint jresult = 0 ;
  int arg1 ;
  int arg2 ;
  SDL_Surface *arg3 = (SDL_Surface *) 0 ;
  int result;
  
  (void)jenv;
  (void)jcls;
  arg1 = (int)jarg1; 
  arg2 = (int)jarg2; 
  arg3 = *(SDL_Surface **)&jarg3; 
  result = (int)Surface_Get_Pixel_G(arg1,arg2,arg3);
  jresult = (jint)result; 
  return jresult;
}


JNIEXPORT jint JNICALL Java_votebox_sdl_SDLJNI_Surface_1Get_1Pixel_1B(JNIEnv *jenv, jclass jcls, jint jarg1, jint jarg2, jlong jarg3) {
  jint jresult = 0 ;
  int arg1 ;
  int arg2 ;
  SDL_Surface *arg3 = (SDL_Surface *) 0 ;
  int result;
  
  (void)jenv;
  (void)jcls;
  arg1 = (int)jarg1; 
  arg2 = (int)jarg2; 
  arg3 = *(SDL_Surface **)&jarg3; 
  result = (int)Surface_Get_Pixel_B(arg1,arg2,arg3);
  jresult = (jint)result; 
  return jresult;
}


JNIEXPORT jint JNICALL Java_votebox_sdl_SDLJNI_Surface_1Get_1Pixel_1A(JNIEnv *jenv, jclass jcls, jint jarg1, jint jarg2, jlong jarg3) {
  jint jresult = 0 ;
  int arg1 ;
  int arg2 ;
  SDL_Surface *arg3 = (SDL_Surface *) 0 ;
  int result;
  
  (void)jenv;
  (void)jcls;
  arg1 = (int)jarg1; 
  arg2 = (int)jarg2; 
  arg3 = *(SDL_Surface **)&jarg3; 
  result = (int)Surface_Get_Pixel_A(arg1,arg2,arg3);
  jresult = (jint)result; 
  return jresult;
}


JNIEXPORT jlong JNICALL Java_votebox_sdl_SDLJNI_Surface_1Get_1Format(JNIEnv *jenv, jclass jcls, jlong jarg1) {
  jlong jresult = 0 ;
  SDL_Surface *arg1 = (SDL_Surface *) 0 ;
  SDL_PixelFormat *result = 0 ;
  
  (void)jenv;
  (void)jcls;
  arg1 = *(SDL_Surface **)&jarg1; 
  result = (SDL_PixelFormat *)Surface_Get_Format(arg1);
  *(SDL_PixelFormat **)&jresult = result; 
  return jresult;
}


JNIEXPORT jlong JNICALL Java_votebox_sdl_SDLJNI_Alloc_1Surface(JNIEnv *jenv, jclass jcls, jint jarg1, jint jarg2) {
  jlong jresult = 0 ;
  int arg1 ;
  int arg2 ;
  SDL_Surface *result = 0 ;
  
  (void)jenv;
  (void)jcls;
  arg1 = (int)jarg1; 
  arg2 = (int)jarg2; 
  result = (SDL_Surface *)Alloc_Surface(arg1,arg2);
  *(SDL_Surface **)&jresult = result; 
  return jresult;
}


JNIEXPORT jlong JNICALL Java_votebox_sdl_SDLJNI_Alloc_1Event(JNIEnv *jenv, jclass jcls) {
  jlong jresult = 0 ;
  SDL_Event *result = 0 ;
  
  (void)jenv;
  (void)jcls;
  result = (SDL_Event *)Alloc_Event();
  *(SDL_Event **)&jresult = result; 
  return jresult;
}


JNIEXPORT void JNICALL Java_votebox_sdl_SDLJNI_Free_1Event(JNIEnv *jenv, jclass jcls, jlong jarg1) {
  SDL_Event *arg1 = (SDL_Event *) 0 ;
  
  (void)jenv;
  (void)jcls;
  arg1 = *(SDL_Event **)&jarg1; 
  Free_Event(arg1);
}


JNIEXPORT jlong JNICALL Java_votebox_sdl_SDLJNI_Alloc_1Rect(JNIEnv *jenv, jclass jcls) {
  jlong jresult = 0 ;
  SDL_Rect *result = 0 ;
  
  (void)jenv;
  (void)jcls;
  result = (SDL_Rect *)Alloc_Rect();
  *(SDL_Rect **)&jresult = result; 
  return jresult;
}


JNIEXPORT void JNICALL Java_votebox_sdl_SDLJNI_Free_1Rect(JNIEnv *jenv, jclass jcls, jlong jarg1) {
  SDL_Rect *arg1 = (SDL_Rect *) 0 ;
  
  (void)jenv;
  (void)jcls;
  arg1 = *(SDL_Rect **)&jarg1; 
  Free_Rect(arg1);
}


#ifdef __cplusplus
}
#endif

