	.align 4
body_foo:
!locals=1, max_args=0
	save %sp,-96,%sp
! [RETURN (PARAM 1)]
	ret
	restore
	ret
	restore

	.align 4
test_foo:
!locals=1, max_args=0
	save %sp,-96,%sp
! [RETURN (PARAM 1)]
	ret
	restore
	ret
	restore

	.global main
	.align 4
main:
!locals=3, max_args=2
	save %sp,-104,%sp
! [MOVE (TEMP 1) (CALL (NAME malloc) ( (NAME wSZ)))]
	mov 4,%o0
	call malloc
	nop
	mov %o0,%l0
!>> Temp t1 assigned to reg %l1
	mov %o0,%l1
! [MOVE (MEM (TEMP 1)) (CONST 0)]
	mov 0,%l0
	st %l0,[%l1]
! [MOVE (VAR 1) (TEMP 1)]
	mov %l1,%l0
	st %l0,[%fp-4]
! [MOVE (TEMP 2) (CALL (NAME test_foo) ( (PARAM 0) (CONST 1)))]
	ld [%fp+68],%l0
	st %l0,[%sp+68]
	ld [1],%l2
	st %l2,[%sp+68]
	call test_foo
	nop
	mov %o0,%l3
!>> Temp t2 assigned to reg %l4
	mov %o0,%l4
! [MOVE (VAR 2) (TEMP 2)]
	mov %l4,%l3
	st %l3,[%fp-8]
! [MOVE (TEMP 3) (CALL (NAME body_foo) ( (VAR 1) (CONST 1)))]
	ld [%fp-4],%l3
	st %l3,[%sp+68]
	ld [1],%l5
	st %l5,[%sp+68]
	call body_foo
	nop
	mov %o0,%l6
!>> Temp t3 assigned to reg %l7
	mov %o0,%l7
! [MOVE (VAR 3) (TEMP 3)]
	mov %l7,%l6
	st %l6,[%fp-12]
! [CALLST (NAME print) ( (VAR 2))]
	ld [%fp-8],%o1
	sethi %hi(L$1),%o0
	or %o0, %lo(L$1),%o0
	call printf
	nop
! [CALLST (NAME print) ( (VAR 3))]
	ld [%fp-12],%o1
	sethi %hi(L$1),%o0
	or %o0, %lo(L$1),%o0
	call printf
	nop
	ret
	restore

L$1:	.asciz "%d\n"

!Total regs:  9
!Total insts: 60
